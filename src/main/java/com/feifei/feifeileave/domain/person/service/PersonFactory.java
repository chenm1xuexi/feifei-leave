package com.feifei.feifeileave.domain.person.service;

import com.feifei.feifeileave.domain.person.entity.Person;
import com.feifei.feifeileave.domain.person.entity.valueobject.PersonStatus;
import com.feifei.feifeileave.domain.person.entity.valueobject.PersonType;
import com.feifei.feifeileave.domain.person.repository.po.PersonPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 人员工厂，用于满足各种需求的人员创建
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 22:43
 */
@Slf4j
@Service
public class PersonFactory {

    // PersonRepository personRepository;

    public Person createPerson(PersonPO po) {
        return new Person()
                .setPersonId(po.getPersonId())
                .setPersonType(PersonType.valueOf(po.getPersonType()))
                .setRoleLevel(po.getRoleLevel())
                .setPersonName(po.getPersonName())
                .setPersonStatus(PersonStatus.valueOf(po.getPersonStatus()))
                .setCreateTime(po.getCreateTime())
                .setUpdateTime(po.getUpdateTime());
    }
}
