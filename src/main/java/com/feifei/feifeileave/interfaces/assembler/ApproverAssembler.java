package com.feifei.feifeileave.interfaces.assembler;

import com.feifei.feifeileave.domain.leave.entity.valueobject.Approver;
import com.feifei.feifeileave.interfaces.dto.ApproverDTO;

/**
 * 审批者转换器
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:37
 */
public interface ApproverAssembler {

    static Approver toDO(ApproverDTO dto) {
        return new Approver()
                .setPersonId(dto.getPersonId())
                .setPersonName(dto.getPersonName());
    }

    static ApproverDTO toDTO(Approver approver) {
        return new ApproverDTO()
                .setPersonId(approver.getPersonId())
                .setPersonName(approver.getPersonName());
    }
}