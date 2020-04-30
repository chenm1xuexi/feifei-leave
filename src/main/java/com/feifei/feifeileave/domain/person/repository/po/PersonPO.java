package com.feifei.feifeileave.domain.person.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@TableName(value = "leave_person")
public class PersonPO {
    /**
     * 资源主键
     */
    @TableId(value = "person_id", type = IdType.ASSIGN_ID)
    Long personId;

    /**
     * 人员名称
     */
    @TableField(value = "person_name")
    String personName;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    Long departmentId;

    /**
     * 人员类型
     */
    @TableField(value = "person_type")
    String personType;

    /**
     * 领导id，来自关联表, 用此来标识关联的可审批人列表
     */
    @TableField(value = "leader_id")
    Long leaderId;

    /**
     * 角色级别
     */
    @TableField(value = "role_level")
    Integer roleLevel;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    Date updateTime;

    /**
     * 用户状态，是否可用
     */
    @TableField(value = "person_status")
    String personStatus;

    public static final String COL_PERSON_ID = "person_id";

    public static final String COL_PERSON_NAME = "person_name";

    public static final String COL_DEPARTMENT_ID = "department_id";

    public static final String COL_PERSON_TYPE = "person_type";

    public static final String COL_LEADER_ID = "leader_id";

    public static final String COL_ROLE_LEVEL = "role_level";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_PERSON_STATUS = "person_status";
}