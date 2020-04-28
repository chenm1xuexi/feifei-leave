package com.feifei.feifeileave.domain.leave.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@TableName(value = "leave_leave")
public class LeavePO {
    /**
     * 资源主键
     */
    @TableId(value = "leave_id", type = IdType.ASSIGN_ID)
    Long leaveId;

    /**
     * 申请人id
     */
    @TableField(value = "applicant_id")
    Long applicantId;

    /**
     * 申请人名称
     */
    @TableField(value = "applicant_name")
    String applicantName;

    /**
     * 申请人类型
     */
    @TableField(value = "applicant_type")
    String applicantType;

    /**
     * 下一个审批人id
     */
    @TableField(value = "approver_id")
    Long approverId;

    /**
     * 下一个审批人名称
     */
    @TableField(value = "approver_name")
    String approverName;

    /**
     * 请假类型
     */
    @TableField(value = "leave_type")
    String leaveType;

    /**
     * 请假单状态，是否已完结
     */
    @TableField(value = "leave_status")
    String leaveStatus;

    /**
     * 请假开始时间
     */
    @TableField(value = "start_time")
    Date startTime;

    /**
     * 请假结束时间
     */
    @TableField(value = "end_time")
    Date endTime;

    /**
     * 请假天数/天
     */
    @TableField(value = "duration")
    Integer duration;

    /**
     * 历史审批意见集合
     */
    @TableField(exist = false)
    List<ApprovalInfoPO> historyApprovalInfos;

    public static final String COL_LEAVE_ID = "leave_id";

    public static final String COL_APPLICANT_ID = "applicant_id";

    public static final String COL_APPLICANT_NAME = "applicant_name";

    public static final String COL_APPLICANT_TYPE = "applicant_type";

    public static final String COL_APPROVER_ID = "approver_id";

    public static final String COL_APPROVER_NAME = "approver_name";

    public static final String COL_LEAVE_TYPE = "leave_type";

    public static final String COL_LEAVE_STATUS = "leave_status";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_DURATION = "duration";
}