package com.feifei.feifeileave.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * 申请人dto
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:18
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicantDTO {

    /**
     * 申请人id
     */
    Long personId;

    /**
     * 申请人名称
     */
    String personName;

    /**
     * 直系领导id
     */
    String leaderId;

    /**
     * 申请单类型
     */
    String applicantType;

    /**
     * 角色级别
     */
    String roleLevel;
}