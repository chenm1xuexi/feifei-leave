package com.feifei.feifeileave.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 审批意见dto
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:24
 */
@Accessors(chain = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApprovalInfoDTO {

    /**
     * 审批意见id
     */
    Long approvalInfoId;

    /**
     * 审批人信息
     */
    ApproverDTO approverDTO;

    /**
     * 审批意见
     */
    String msg;

    /**
     * 审批时间
     */
    Long time;
}