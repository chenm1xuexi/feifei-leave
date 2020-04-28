package com.feifei.feifeileave.domain.leave.event;

/**
 * 请假事件类型 值对象
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 16:56
 */
public enum LeaveEventType {
    /**
     * 创建请假事件
     */
    CREATE_EVENT,

    /**
     * 同意请假事件
     */
    AGREE_EVENT,

    /**
     * 拒绝请假事件
     */
    REJECT_EVENT,

    /**
     * 批准请假事件
     */
    APPROVED_EVENT
}