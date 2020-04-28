package com.feifei.feifeileave.domain.leave.service;

import com.feifei.feifeileave.domain.leave.entity.ApprovalInfo;
import com.feifei.feifeileave.domain.leave.entity.Leave;
import com.feifei.feifeileave.domain.leave.entity.valueobject.Applicant;
import com.feifei.feifeileave.domain.leave.entity.valueobject.Approver;
import com.feifei.feifeileave.domain.leave.repository.po.ApprovalInfoPO;
import com.feifei.feifeileave.domain.leave.repository.po.LeavePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 请假单工厂，为了满足多条件下的请假单创建
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 23:20
 */
@Slf4j
@Service
public class LeaveFactory {

    /**
     * 创建请假单po
     *
     * @param leave 请假实体
     * @return
     * @author shixiongfei
     * @date 2020/4/28 8:07 下午
     */
    public LeavePO createLeavePO(Leave leave) {
        return new LeavePO()
                .setLeaveId(leave.getLeaveId())
                .setApplicantId(leave.getApplicant().getPersonId())
                .setApplicantName(leave.getApplicant().getPersonName())
                .setApproverId(leave.getApprover().getPersonId())
                .setApproverName(leave.getApprover().getPersonName())
                .setStartTime(leave.getStartTime())
                .setLeaveStatus(leave.getLeaveStatus().toString())
                .setHistoryApprovalInfos(approvalInfoPOListFromDO(leave));
    }

    public List<ApprovalInfoPO> approvalInfoPOListFromDO(Leave leave) {
        return leave.getHistoryApprovalInfos().stream()
                .map(info -> new ApprovalInfoPO()
                        .setLeaveId(leave.getLeaveId())
                        .setApproverId(info.getApprover().getPersonId())
                        .setApproverLevel(info.getApprover().getLevel())
                        .setApproverName(info.getApprover().getPersonName())
                        .setApprovalInfoId(info.getApprovalInfoId())
                        .setMsg(info.getMsg())
                        .setTime(info.getApprovalTime()))
                .collect(Collectors.toList());
    }

    public Leave getLeave(LeavePO po) {
        Leave leave = new Leave();
        Applicant applicant = new Applicant()
                .setPersonId(po.getApplicantId())
                .setPersonName(po.getApplicantName());
        Approver approver = new Approver()
                .setPersonId(po.getApproverId())
                .setPersonName(po.getApproverName());
        List<ApprovalInfo> infos = po.getHistoryApprovalInfos().stream()
                .map(this::approvalInfoFromPO)
                .collect(Collectors.toList());
        return leave.setApplicant(applicant)
                .setApprover(approver)
                .setHistoryApprovalInfos(infos)
                .setStartTime(leave.getStartTime())
                .setLeaveStatus(leave.getLeaveStatus());
    }

    public ApprovalInfo approvalInfoFromPO(ApprovalInfoPO info) {
        Approver approver = new Approver()
                .setPersonId(info.getApproverId())
                .setPersonName(info.getApproverName())
                .setLevel(info.getApproverLevel());
        return new ApprovalInfo()
                .setApprovalInfoId(info.getApprovalInfoId())
                .setApprover(approver)
                .setMsg(info.getMsg())
                .setApprovalTime(info.getTime());
    }

}