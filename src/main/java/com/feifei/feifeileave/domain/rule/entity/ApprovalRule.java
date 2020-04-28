package com.feifei.feifeileave.domain.rule.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 审批规则实体 也是审批规则聚合的聚合根
 *
 * 人员类型和请假类型联合组合作为实体的唯一标识
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 16:35
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ApprovalRule {

    /**
     * 人员类型
     */
    String personType;

    /**
     * 请假类型
     */
    String leaveType;

    /**
     * 审批持续时间，过期作废
     */
    Long duration;

    /**
     * 最高领导审批级别
     */
    Integer maxLeaderLevel;
}