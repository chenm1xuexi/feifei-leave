package com.feifei.feifeileave.domain.leave.entity;

import com.feifei.feifeileave.domain.leave.entity.valueobject.ApprovalType;
import com.feifei.feifeileave.domain.leave.entity.valueobject.Approver;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 审批意见实体
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 14:33
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ApprovalInfo {

    /**
     * 审批意见唯一标识，在聚合内唯一
     */
    Long approvalInfoId;

    /**
     * 审批人，值对象，请假人选定后要不不修改，要不全局替换
     */
    Approver approver;

    /**
     * 审批类型
     */
    ApprovalType approvalType;

    /**
     * 审批意见
     */
    String msg;

    /**
     * 审批时间
     */
    Long approvalTime;
}