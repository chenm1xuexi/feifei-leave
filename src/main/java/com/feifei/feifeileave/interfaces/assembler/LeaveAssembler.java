package com.feifei.feifeileave.interfaces.assembler;

import com.feifei.feifeileave.domain.leave.entity.ApprovalInfo;
import com.feifei.feifeileave.domain.leave.entity.Leave;
import com.feifei.feifeileave.interfaces.dto.ApprovalInfoDTO;
import com.feifei.feifeileave.interfaces.dto.LeaveDTO;
import org.assertj.core.util.DateUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dto do po 转换器
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:05
 */
public interface LeaveAssembler {

    static Leave toDO(LeaveDTO dto) {
        List<ApprovalInfo> infos = dto.getHistoryApprovalInfoDTOList().stream()
                .map(info -> ApprovalInfoAssembler.toDO(dto.getCurrentApprovalInfoDTO()))
                .collect(Collectors.toList());
        return new Leave().setLeaveId(dto.getLeaveId())
                .setApplicant(ApplicantAssembler.toDO(dto.getApplicantDTO()))
                .setApprover(ApproverAssembler.toDO(dto.getApproverDTO()))
                .setApprovalInfo(ApprovalInfoAssembler.toDO(dto.getCurrentApprovalInfoDTO()))
                .setHistoryApprovalInfos(infos);

    }

    static LeaveDTO toDTO(Leave leave) {
        List<ApprovalInfoDTO> list = leave.getHistoryApprovalInfos()
                .stream()
                .map(ApprovalInfoAssembler::toDTO)
                .collect(Collectors.toList());
        return new LeaveDTO().setLeaveId(leave.getLeaveId())
                .setLeaveStatus(leave.getLeaveStatus().toString())
                .setLeaveType(leave.getType().toString())
                .setStartTime(DateUtil.formatAsDatetime(leave.getStartTime()))
                .setEndTime(DateUtil.formatAsDatetime(leave.getEndTime()))
                .setCurrentApprovalInfoDTO(ApprovalInfoAssembler.toDTO(leave.getApprovalInfo()))
                .setHistoryApprovalInfoDTOList(list);
    }
}
