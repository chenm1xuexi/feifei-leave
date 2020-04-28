package com.feifei.feifeileave.domain.leave.entity.valueobject;

/**
 * 请假单状态
 *
 * @author shixiongfei
 * @date 2020/4/21 3:03 下午
 */
public enum LeaveStatus {

    /**
     * 审批中
     */
    APPROVING,

    /**
     * 已批准
     */
    APPROVED,

    /**
     * 拒绝
     */
    REJECTED;
}