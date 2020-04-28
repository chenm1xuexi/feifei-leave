package com.feifei.feifeileave.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * TODO description
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:22
 */
@Accessors(chain = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApproverDTO {

    /**
     * 审批人id
     */
    Long personId;

    /**
     * 审批人名称
     */
    String personName;
}