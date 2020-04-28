package com.feifei.feifeileave.application.impl;

import com.feifei.feifeileave.application.LeaveApplicationService;
import com.feifei.feifeileave.domain.leave.entity.Leave;
import com.feifei.feifeileave.domain.leave.entity.valueobject.Approver;
import com.feifei.feifeileave.domain.leave.service.LeaveDomainService;
import com.feifei.feifeileave.domain.person.entity.Person;
import com.feifei.feifeileave.domain.person.service.PersonDomainService;
import com.feifei.feifeileave.domain.rule.service.ApprovalRuleDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 请假应用服务具体实现类,应用服务可以跨聚合进行调用，主要是为了实现服务之间的编排和组合转发
 * 应用层也可以直接调用仓储层的接口（切记应用层不可包含领域层的业务逻辑）
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 18:58
 */
@Slf4j
@Service
@AllArgsConstructor
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    ApprovalRuleDomainService approvalRuleDomainService;

    PersonDomainService personDomainService;

    LeaveDomainService leaveDomainService;

    @Override
    public void createLeaveInfo(Leave leave) {
        // 1.根据请假单定义的人员类型、请假类型和请假时长从 rule 聚合中获取请假审批规则
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(leave.getType().toString(), leave.getApplicant().getPersonType(), leave.getDuration());
        // 2. 根据审批规则的级别和申请人来获取下一个审批人（申请人的leader）
        Person approver = personDomainService.findFirstApprover(leave.getApplicant().getPersonId(), leaderMaxLevel);
        // 3. 创建请假单
        leaveDomainService.createLeave(leave, leaderMaxLevel, Approver.fromPerson(approver));
    }

    @Override
    public void updateLeaveInfo(Leave leave) {
        leaveDomainService.updateLeaveInfo(leave);
    }

    @Override
    public void submitApproval(Leave leave) {
        // 1. 提交审批前，需要找到当前审批人指定的下一个审批人信息
        Person nextApprover = personDomainService.findNextApprover(leave.getApprover().getPersonId(), leave.getLeaderMaxLevel());
        // 2. 当前审批人提交审批
        leaveDomainService.submitApproval(leave, Approver.fromPerson(nextApprover));
    }

    @Override
    public Leave findById(Long leaveId) {
        return leaveDomainService.findByLeaveId(leaveId);
    }
}