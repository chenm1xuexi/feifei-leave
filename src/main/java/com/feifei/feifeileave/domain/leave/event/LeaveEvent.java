package com.feifei.feifeileave.domain.leave.event;

import com.feifei.feifeileave.domain.leave.entity.Leave;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;


/**
 * 请假事件实体
 * 当满足某请假条件时，触发事件
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 16:48
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaveEvent extends ApplicationEvent {

    /**
     * 请假事件类型
     */
    private LeaveEventType leaveEventType;

    public LeaveEvent(Object source, LeaveEventType leaveEventType) {
        super(source);
        this.leaveEventType = leaveEventType;
    }

    /**
     * 根据请假事件类型和请假单，来创建一个请假事件
     * 满足充血模型, 如果不涉及到内部的一些逻辑的话可直接在领域层中new
     * 这里写这个方法是希望开发人员对实体对象的正确认识，实体自己的业务逻辑应该交由自身去实现
     *
     * @author shixiongfei
     * @date 2020/4/21 4:58 下午
     * @return
     */
    public static LeaveEvent create(LeaveEventType leaveEventType, Leave leave) {
        return new LeaveEvent(leave, leaveEventType);
    }
}