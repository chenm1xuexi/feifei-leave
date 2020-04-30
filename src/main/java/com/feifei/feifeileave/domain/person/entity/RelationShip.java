package com.feifei.feifeileave.domain.person.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 人员上下级关系 实体
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 16:11
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class RelationShip {

    /**
     * 唯一标识
     */
    Long id;

    /**
     * 人员id
     */
    String personId;

    /**
     * leader id， 上级审批领导id
     */
    String leaderId;

    Integer leaderLevel;
}