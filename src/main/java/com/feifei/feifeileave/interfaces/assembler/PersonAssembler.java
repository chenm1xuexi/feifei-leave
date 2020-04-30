package com.feifei.feifeileave.interfaces.assembler;

import com.feifei.feifeileave.domain.person.entity.Person;
import com.feifei.feifeileave.domain.person.entity.valueobject.PersonStatus;
import com.feifei.feifeileave.domain.person.entity.valueobject.PersonType;
import com.feifei.feifeileave.interfaces.dto.PersonDTO;

/**
 * do dto互转
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 18:00
 */
public interface PersonAssembler {


    static Person toDO(PersonDTO personDTO) {
        return new Person()
                .setPersonId(personDTO.getPersonId())
                .setPersonType(PersonType.valueOf(personDTO.getPersonType()))
                .setPersonName(personDTO.getPersonName())
                .setCreateTime(personDTO.getCreateTime())
                .setUpdateTime(personDTO.getUpdateTime())
                .setPersonStatus(PersonStatus.valueOf(personDTO.getPersonStatus()));
    }
}
