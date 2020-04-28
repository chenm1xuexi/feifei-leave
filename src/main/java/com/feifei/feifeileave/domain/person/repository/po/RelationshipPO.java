package com.feifei.feifeileave.domain.person.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "leave_relationship")
public class RelationshipPO {
    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 人员id
     */
    @TableField(value = "person_id")
    private Long personId;

    /**
     * 上级id
     */
    @TableField(value = "leader_id")
    private Long leaderId;

    public static final String COL_ID = "id";

    public static final String COL_PERSON_ID = "person_id";

    public static final String COL_LEADER_ID = "leader_id";
}