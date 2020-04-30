package com.feifei.feifeileave.infrastructure.listener.leave;

import com.feifei.feifeileave.domain.leave.event.LeaveEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 请假单事件监听器，为什么要放在基础设施层是因为考虑到对聚合进行拆分成微服务时，
 * 基础设施层会一起被放置到新的微服务中
 * 考虑到一种场景 原来在存在2个及以上的聚合间相互调用，我们一般采用领域事件来屏蔽多个聚合间的直接调用
 * 如果其中一个聚合抽离成了微服务，那么原先进行的服务间调用只需要在应用层和基础设施层将对应的domainService修改成rpc调用或者rest
 * 不会对领域层造成任何的修改(这只是我个人对DDD的理解，仅代表个人观点)
 *
 * @Author: shixiongfei
 * @Date: 2020/4/28 17:07
 */
@Slf4j
@Component
public class LeaveEventListener {

    /**
     * 请假单创建成功时，触发此监听器执行
     *
     * @param event 事件实体
     * @return
     * @author shixiongfei
     * @date 2020/4/28 5:10 下午
     */
    @EventListener(classes = LeaveEvent.class, condition = "#event.leaveEventType == T(com.feifei.feifeileave.domain.leave.event.LeaveEventType).CREATE_EVENT")
    public void createLeave(LeaveEvent event) {
        // 这里只是做一个模拟，采用mq的方式调用消息微服务去发送短信或者其他的通知方式
        log.info("请假单创建成功事件，监听器开始执行");
        // mq.sendCreateSMS();
        log.info("请假单创建成功事件，监听器执行完成");
    }

    /**
     * 请假单审批被拒绝时，触发此监听器执行
     *
     * @param event 事件实体
     * @return
     * @author shixiongfei
     * @date 2020/4/28 5:10 下午
     */
    @EventListener(classes = LeaveEvent.class, condition = "#event.leaveEventType == T(com.feifei.feifeileave.domain.leave.event.LeaveEventType).REJECT_EVENT")
    public void rejectLeave(LeaveEvent event) {
        // 这里只是做一个模拟，采用mq的方式调用消息微服务去发送短信或者其他的通知方式
        log.info("请假单拒绝事件，监听器开始执行");
        // mq.sendRejectSMS();
        log.info("请假单拒绝事件，监听器执行完成");
    }

    /**
     * 请假单审批通过时，触发此监听器执行
     *
     * @param event 事件实体
     * @return
     * @author shixiongfei
     * @date 2020/4/28 5:10 下午
     */
    @EventListener(classes = LeaveEvent.class, condition = "#event.leaveEventType == T(com.feifei.feifeileave.domain.leave.event.LeaveEventType).AGREE_EVENT")
    public void agreeLeave(LeaveEvent event) {
        // 这里只是做一个模拟，采用mq的方式调用消息微服务去发送短信或者其他的通知方式
        log.info("请假单审批通过事件，监听器开始执行");
        // mq.sendAgreeSMS();
        log.info("请假单审批通过事件，监听器执行完成");
    }

    /**
     * 请假单审批完成时，触发此监听器执行
     *
     * @param event 事件实体
     * @return
     * @author shixiongfei
     * @date 2020/4/28 5:10 下午
     */
    @EventListener(classes = LeaveEvent.class, condition = "#event.leaveEventType == T(com.feifei.feifeileave.domain.leave.event.LeaveEventType).APPROVED_EVENT")
    public void approvedLeave(LeaveEvent event) {
        // 这里只是做一个模拟，采用mq的方式调用消息微服务去发送短信或者其他的通知方式
        log.info("请假单审批完成事件，监听器开始执行");
        // mq.sendApproveSMS();
        log.info("请假单审批完成事件，监听器执行完成");
    }
}