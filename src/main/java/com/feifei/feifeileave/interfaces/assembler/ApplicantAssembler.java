package com.feifei.feifeileave.interfaces.assembler;

import com.feifei.feifeileave.domain.leave.entity.valueobject.Applicant;
import com.feifei.feifeileave.interfaces.dto.ApplicantDTO;

/**
 * 申请者转换器
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:33
 */

public interface ApplicantAssembler {

    static Applicant toDO(ApplicantDTO dto){
        return new Applicant()
        .setPersonId(dto.getPersonId())
        .setPersonName(dto.getPersonName());
    }
}