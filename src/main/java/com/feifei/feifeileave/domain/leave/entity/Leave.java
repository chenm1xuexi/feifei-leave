package com.feifei.feifeileave.domain.leave.entity;

import com.feifei.feifeileave.domain.leave.entity.valueobject.Applicant;
import com.feifei.feifeileave.domain.leave.entity.valueobject.Approver;
import com.feifei.feifeileave.domain.leave.entity.valueobject.LeaveStatus;
import com.feifei.feifeileave.domain.leave.entity.valueobject.LeaveType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 请假单实体，为请假聚合中的聚合根
 * <p>
 * 为了满足职责和边界清晰，强烈建议聚合内部的业务交由自身处理，
 * 设计多个实体组合的业务交由聚合的领域服务处理
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 14:28
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Leave {

    /**
     * 请假单唯一标识，要访问聚合内的实体必须通过此标识来访问
     */
    Long leaveId;

    /**
     * 申请人 值对象
     */
    Applicant applicant;

    /**
     * 下一审批人，值对象
     */
    Approver approver;

    /**
     * 请假单类型
     */
    LeaveType type;

    /**
     * 请假单审批状态 值对象
     */
    LeaveStatus leaveStatus;

    /**
     * 审批开时间
     */
    Date startTime;

    /**
     * 审批结束时间
     */
    Date endTime;

    /**
     * 审批花费时长（毫秒）
     */
    Long duration;

    /**
     * 请假天数
     */
    Integer daysOff;

    /**
     * 审批领导最高级别
     */
    Integer leaderMaxLevel;

    /**
     * 当前审批意见， 实体对象
     */
    ApprovalInfo approvalInfo;

    /**
     * 历史审批意见集合
     */
    List<ApprovalInfo> historyApprovalInfos;

    /**
     * 初始化请假单
     *
     * @author shixiongfei
     * @date 2020/4/21 11:01 下午
     */
    public Leave create() {
        return this.setStartTime(new Date())
                .setLeaveStatus(LeaveStatus.APPROVING);
    }

    /**
     * 审批拒绝，申请单状态为驳回，审批完结
     *
     */
    public Leave reject() {
        return this.setApprover(null)
                .setLeaveStatus(LeaveStatus.REJECTED);
    }

    /**
     * 审批通过，设置下一个审批人
     * @param nextApprover
     */
    public Leave agree(Approver nextApprover) {
        return this.setApprover(nextApprover)
                .setLeaveStatus(LeaveStatus.APPROVING);
    }

    /**
     * 请假单审批完成，同意请假
     * @return
     */
    public Leave finish() {
        return this.setApprover(null)
                .setLeaveStatus(LeaveStatus.APPROVED)
                .setEndTime(new Date())
                .setDuration(getDuration());
    }

    public long getDuration() {
        return endTime.getTime() - startTime.getTime();
    }

    /**
     * 添加审批意见到历史审批意见集合中
     *
     * @author shixiongfei
     * @date 2020/4/28 9:04 下午
     * @param approvalInfo 审批意见
     * @return
     */
    public Leave addHistoryApprovalInfo(ApprovalInfo approvalInfo) {
        if (Objects.isNull(historyApprovalInfos)) {
            historyApprovalInfos = new ArrayList<>();
        }
        this.historyApprovalInfos.add(approvalInfo);
        return this;
    }
}