package com.feifei.feifeileave.domain.leave.repository.persistence;

import com.feifei.feifeileave.domain.leave.repository.facade.LeaveRepository;
import com.feifei.feifeileave.domain.leave.repository.mapper.ApprovalInfoMapper;
import com.feifei.feifeileave.domain.leave.repository.mapper.LeaveMapper;
import com.feifei.feifeileave.domain.leave.repository.po.ApprovalInfoPO;
import com.feifei.feifeileave.domain.leave.repository.po.LeavePO;
import com.feifei.feifeileave.infrastructure.LeaveException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 请假聚合仓储服务具体实现
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 17:22
 */
@Slf4j
@Repository
@AllArgsConstructor
public class LeaveRepositoryImpl implements LeaveRepository {

    LeaveMapper leaveMapper;

    ApprovalInfoMapper approvalInfoMapper;

    @Override
    public void save(LeavePO leavePO) {
        leaveMapper.insert(leavePO);
        List<ApprovalInfoPO> approvalInfos = leavePO.getHistoryApprovalInfos();
        // 填充主键id
        approvalInfos.forEach(po -> po.setLeaveId(leavePO.getLeaveId()));
        approvalInfoMapper.saveApprovalInfos(leavePO.getHistoryApprovalInfos());
    }

    @Override
    public void updateApproval(LeavePO leavePO) {
        leaveMapper.updateById(leavePO);
        // 添加审批意见
        ApprovalInfoPO approvalInfoPO = leavePO.getHistoryApprovalInfos().get(0);
        approvalInfoMapper.insert(approvalInfoPO);
    }

    @Override
    public LeavePO findByLeaveId(Long leaveId) {
        LeavePO leavePO = Optional.ofNullable(leaveMapper.selectById(leaveId))
                .orElseThrow(() -> new LeaveException("not exists leave info => leaveId = " + leaveId));
        List<ApprovalInfoPO> infoPOS = approvalInfoMapper.listApprovalInfoByLeaveId(leaveId);
        leavePO.setHistoryApprovalInfos(infoPOS);
        return leavePO;
    }

    @Override
    public List<LeavePO> listByApplicantId(String applicantId) {
        List<LeavePO> leavePOList =
                Optional.ofNullable(leaveMapper.listByApplicantId(applicantId))
                        .orElse(Collections.emptyList());
        // 获取历史审批意见列表
        leavePOList.forEach(po -> po.setHistoryApprovalInfos(approvalInfoMapper.listApprovalInfoByLeaveId(po.getLeaveId())));
        return leavePOList;
    }

    @Override
    public List<LeavePO> listByApproverId(String approverId) {
        List<LeavePO> leavePOList =
                Optional.ofNullable(leaveMapper.listByApproverId(approverId))
                        .orElse(Collections.emptyList());
        // 获取历史审批意见列表
        leavePOList.forEach(po -> po.setHistoryApprovalInfos(approvalInfoMapper.listApprovalInfoByLeaveId(po.getLeaveId())));
        return leavePOList;
    }
}