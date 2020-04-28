package com.feifei.feifeileave.domain.person.entity;

import com.feifei.feifeileave.domain.person.entity.valueobject.PersonStatus;
import com.feifei.feifeileave.domain.person.entity.valueobject.PersonType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

/**
 * 用户实体，在用户聚合中为聚合根
 *
 * 用户聚合用于管理用户信息和上下级关系
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 15:26
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Person {

    /**
     * 用户唯一标识，聚合外访问当前聚合下的实体必须通过此标识来完成
     */
    Long personId;

    /**
     * 人员名称
     */
    String personName;

    /**
     * 员工类型 值对象
     */
    PersonType personType;

    /**
     * 人员上下级关系列表
     */
    List<RelationShip> relationShips;

    /**
     * 人员角色等级
     */
    int roleLevel;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 修改时间
     */
    Date updateTime;

    /**
     * 人员状态，是否可用
     */
    PersonStatus personStatus;
}