package com.feifei.feifeileave.application;

import com.feifei.feifeileave.domain.leave.entity.Leave;

/**
 * 请假应用服务
 * 用于请假服务的编排和转发，该层不可参杂业务逻辑，不然会违反ddd的设计思想
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 18:57
 */
public interface LeaveApplicationService {

    /**
     *  创建一个请假申请并为审批人生成任务
     *
     * @author shixiongfei
     * @date 2020/4/21 7:48 下午
     * @param leave 申请单实体
     * @return
     */
    void createLeaveInfo(Leave leave);

    /**
     *  对现有的请假单进行编辑
     *
     * @author shixiongfei
     * @date 2020/4/28 4:04 下午
     * @param leave
     * @return
     */
    void updateLeaveInfo(Leave leave);

    /**
     *  提交申请单
     *
     * @author shixiongfei
     * @date 2020/4/28 7:29 下午
     * @param leave 请假单实体
     * @return
     */
    void submitApproval(Leave leave);


    /**
     * 通过请假单标识获取请假单详情
     *
     * @author shixiongfei
     * @date 2020/4/28 9:48 下午
     * @param leaveId 请假唯一标识
     * @return
     */
    Leave findById(Long leaveId);
}