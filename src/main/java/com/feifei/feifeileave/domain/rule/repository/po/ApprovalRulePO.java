package com.feifei.feifeileave.domain.rule.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "leave_approval_rule")
public class ApprovalRulePO {

    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 请假类型
     */
    @TableField(value = "leave_type")
    private String leaveType;

    /**
     * 人员类型
     */
    @TableField(value = "person_type")
    private String personType;

    /**
     * 请假时长 按半日0.5作为单位计算
     */
    @TableField(value = "duration")
    private Double duration;

    /**
     * 审批领导的最高级别
     */
    @TableField(value = "max_leader_level")
    private Integer maxLeaderLevel;

    public static final String COL_ID = "id";

    public static final String COL_LEAVE_TYPE = "leave_type";

    public static final String COL_PERSON_TYPE = "person_type";

    public static final String COL_DURATION = "duration";

    public static final String COL_MAX_LEADER_LEVEL = "max_leader_level";
}