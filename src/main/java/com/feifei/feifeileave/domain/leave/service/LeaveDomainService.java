package com.feifei.feifeileave.domain.leave.service;

import com.feifei.feifeileave.domain.leave.entity.Leave;
import com.feifei.feifeileave.domain.leave.entity.valueobject.ApprovalType;
import com.feifei.feifeileave.domain.leave.entity.valueobject.Approver;
import com.feifei.feifeileave.domain.leave.event.LeaveEvent;
import com.feifei.feifeileave.domain.leave.event.LeaveEventType;
import com.feifei.feifeileave.domain.leave.repository.facade.LeaveRepository;
import com.feifei.feifeileave.domain.leave.repository.mapper.ApprovalInfoMapper;
import com.feifei.feifeileave.domain.leave.repository.mapper.LeaveMapper;
import com.feifei.feifeileave.domain.leave.repository.po.ApprovalInfoPO;
import com.feifei.feifeileave.domain.leave.repository.po.LeavePO;
import com.feifei.feifeileave.infrastructure.LeaveException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.api.Assertions;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 请假 领域服务 切记领域服务只用来调用自身聚合内部的业务，明确业务边界
 * 不可跨聚合调用，如果需要跨聚合调用，请在应用服务中完成实现
 * 聚合可能会在后面拆分为微服务，因此不可包含边界外的业务
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 22:53
 */
@Slf4j
@Service
@AllArgsConstructor
public class LeaveDomainService {

    LeaveRepository leaveRepository;

    /**
     * 这里直接引用mapper目的是为了对部分非组装的sql可以直接采用mybatis-plus来调用
     * 免得每次简单的查询都需要在leaveRepository中写一遍，感觉这样就没啥意义了
     * 我们学习的是DDD的思想，ddd倡导找到适合自身业务的领域模型，所以不要太刻板
     */
    LeaveMapper leaveMapper;

    ApprovalInfoMapper approvalInfoMapper;

    LeaveFactory leaveFactory;

    ApplicationEventPublisher eventPublisher;

    /**
     * 根据请假单，审批规则最大级别，下一个审批人来创建一个请假单
     *
     * @param leave          请假单定义相关信息
     * @param leaderMaxLevel 审批规则最大角色级别
     * @param fromPerson     下一个审批人
     * @return void
     * @author shixiongfei
     * @date 2020/4/21 10:57 下午
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = LeaveException.class)
    public void createLeave(Leave leave, int leaderMaxLevel, Approver fromPerson) {
        leave.setLeaderMaxLevel(leaderMaxLevel)
                .setApprover(fromPerson);
        // 初始化请假单
        leave.create();
        // 持久化请假单
        LeavePO leavePO = leaveFactory.createLeavePO(leave);
        // 持久层的crud代码尽量不要嵌入到领域层中
        leaveRepository.save(leavePO);
        leave.setLeaveId(leavePO.getLeaveId());
        // 持久化完成后，触发新增事件，SMS、EMS通知申请人请假单创建成功
        LeaveEvent leaveEvent = LeaveEvent.create(LeaveEventType.CREATE_EVENT, leave);
        // 发布事件，监听该事件的监听器会执行相应逻辑
        // 也可以直接采用spring的事件驱动来完成实现（个人更喜欢这种方式）
        eventPublisher.publishEvent(leaveEvent);
    }

    /**
     * 更新请假单实体信息
     *
     * @param leave 请假单实体对象
     * @return
     * @author shixiongfei
     * @date 2020/4/28 4:19 下午
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = LeaveException.class)
    public void updateLeaveInfo(Leave leave) {
        Long leaveId = leave.getLeaveId();
        LeavePO po = leaveMapper.selectById(leaveId);
        if (Objects.isNull(po)) {
            // 这里打错误日志是为了方便以后线上日志错误排查
            log.error("not exists leave info => leaveId = {}", leaveId);
            // 这里抛出异常是为了提供前端友好错误提示
            throw new LeaveException("不存在此请假单信息");
        }

        List<ApprovalInfoPO> infoPOS = approvalInfoMapper.listApprovalInfoByLeaveId(leaveId);
        if (CollectionUtils.isNotEmpty(infoPOS)) {
            throw new LeaveException("请假单处于审批中, 不可修改");
        }
        leaveMapper.updateById(po);
    }

    /**
     * 审批人提交审批
     *
     * @param leave        请假单实体
     * @param nextApprover 下一审批人
     * @return
     * @author shixiongfei
     * @date 2020/4/28 8:33 下午
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = LeaveException.class)
    public void submitApproval(Leave leave, Approver nextApprover) {
        LeaveEvent leaveEvent = null;
        ApprovalType approvalType = leave.getApprovalInfo().getApprovalType();
        // 判断当前审批是否为拒绝
        if (ApprovalType.REJECT == approvalType) {
            // 拒绝则直接将请假单状态设置为驳回驳回，审批完结
            leave.reject();
            // 触发请假单拒绝事件
            leaveEvent = LeaveEvent.create(LeaveEventType.REJECT_EVENT, leave);
        }
        // 因为个人很讨厌多个if else嵌套 所以在这里采用卫语句来处理
        if (ApprovalType.AGREE == approvalType) {
            // 判断是否存在下一个审批人，存在则请假单状态设置为审批中，不存在则完结，请假单状态为通过
            if (Objects.nonNull(nextApprover)) {
                leave.agree(nextApprover);
                // 触发当前审批通过事件
                leaveEvent = LeaveEvent.create(LeaveEventType.AGREE_EVENT, leave);
            } else {
                // 审批完结，请假单状态为同意
                leave.finish();
                // 触发完成事件
                leaveEvent = LeaveEvent.create(LeaveEventType.APPROVED_EVENT, leave);
            }
        }
        // 将审批意见添加到实体中
        leave.addHistoryApprovalInfo(leave.getApprovalInfo());
        // 更新请假单审批相关信息
        leaveRepository.updateApproval(leaveFactory.createLeavePO(leave));
        Assertions.assertThat(leaveEvent).isNotNull();
        // 发布事件
        eventPublisher.publishEvent(leaveEvent);
    }

    /**
     * 通过请假单标识获取leave详情
     *
     * @param leaveId
     * @return
     * @author shixiongfei
     * @date 2020/4/28 9:49 下午
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED, rollbackFor = LeaveException.class)
    public Leave findByLeaveId(Long leaveId) {
        LeavePO po = leaveRepository.findByLeaveId(leaveId);
        return leaveFactory.getLeave(po);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED, rollbackFor = LeaveException.class)
    public List<Leave> listByApplicant(String applicantId) {
        List<LeavePO> leavePOList = leaveRepository.listByApplicantId(applicantId);
        return leavePOList.stream()
                .map(leaveFactory::getLeave)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED, rollbackFor = LeaveException.class)
    public List<Leave> listByApprover(String approverId) {
        List<LeavePO> leavePOList = leaveRepository.listByApproverId(approverId);
        return leavePOList.stream()
                .map(leaveFactory::getLeave)
                .collect(Collectors.toList());
    }
}