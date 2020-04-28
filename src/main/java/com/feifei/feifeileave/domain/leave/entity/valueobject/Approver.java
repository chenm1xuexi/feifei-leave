package com.feifei.feifeileave.domain.leave.entity.valueobject;

import com.feifei.feifeileave.domain.person.entity.Person;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 审批人，值对象
 * 一般值对象不可变，要不全局替换
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 14:39
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Approver {

    /**
     * 人员id
     */
    Long personId;

    /**
     * 人员名称
     */
    String personName;

    /**
     * 审批级别
     */
    Integer level;

    public static Approver fromPerson(Person person) {
        return new Approver()
                .setPersonId(person.getPersonId())
                .setPersonName(person.getPersonName())
                .setLevel(person.getRoleLevel());
    }
}