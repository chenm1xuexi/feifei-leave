package com.feifei.feifeileave.domain.leave.entity.valueobject;

/**
 *  请假类型
 *
 * @author shixiongfei
 * @date 2020/4/21 2:54 下午
 */
public enum LeaveType {

    /**
     * 年假
     */
    ANNUAL,

    /**
     * 产假
     */
    MATERNITY,

    /**
     * 陪产假
     */
    paternity,

    /**
     * 病假
     */
    SICK,

    /**
     * 事假
     */
    PERSONAL,

    /**
     * 婚假
     */
    FUNERAL,

    /**
     * 丧假
     */
    MENSTRUATION,

    /**
     * 生理假
     */
    LEAVE_FOR_RELIGIOUS,

    /**
     * 其他
     */
    OTHERS;
}