package com.feifei.feifeileave.domain.leave.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@TableName(value = "leave_approval_info")
public class ApprovalInfoPO {
    /**
     * 资源主键
     */
    @TableId(value = "approval_info_id", type = IdType.ASSIGN_ID)
    Long approvalInfoId;

    /**
     * 请假单id
     */
    @TableField(value = "leave_id")
    Long leaveId;

    /**
     * 申请人id
     */
    @TableField(value = "applicant_Id")
    Long applicantId;

    /**
     * 审批人id
     */
    @TableField(value = "approver_id")
    Long approverId;

    /**
     * 当前审批人名称
     */
    @TableField(value = "approver_name")
    String approverName;

    /**
     * 当前审批的角色级别
     */
    @TableField(value = "approver_level")
    Integer approverLevel;

    /**
     * 审批意见
     */
    @TableField(value = "msg")
    String msg;

    /**
     * 审批时长
     */
    @TableField(value = "time")
    Long time;

    public static final String COL_APPROVAL_INFO_ID = "approval_info_id";

    public static final String COL_LEAVE_ID = "leave_id";

    public static final String COL_APPLICANT_ID = "applicant_Id";

    public static final String COL_APPROVER_ID = "approver_id";

    public static final String COL_APPROVER_NAME = "approver_name";

    public static final String COL_APPROVER_LEVEL = "approver_level";

    public static final String COL_MSG = "msg";

    public static final String COL_TIME = "time";
}