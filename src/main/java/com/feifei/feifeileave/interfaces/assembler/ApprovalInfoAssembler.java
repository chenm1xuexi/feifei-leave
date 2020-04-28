package com.feifei.feifeileave.interfaces.assembler;

import com.feifei.feifeileave.domain.leave.entity.ApprovalInfo;
import com.feifei.feifeileave.interfaces.dto.ApprovalInfoDTO;

/**
 * TODO description
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:36
 */
public interface ApprovalInfoAssembler {

    static ApprovalInfo toDO(ApprovalInfoDTO dto) {
        return new ApprovalInfo()
                .setApprovalInfoId(dto.getApprovalInfoId())
                .setMsg(dto.getMsg())
                .setApprover(ApproverAssembler.toDO(dto.getApproverDTO()));
    }

    static ApprovalInfoDTO toDTO(ApprovalInfo approvalInfo) {
        return new ApprovalInfoDTO()
                .setApprovalInfoId(approvalInfo.getApprovalInfoId())
                .setApproverDTO(ApproverAssembler.toDTO(approvalInfo.getApprover()))
                .setMsg(approvalInfo.getMsg())
                .setTime(approvalInfo.getApprovalTime());
    }
}
