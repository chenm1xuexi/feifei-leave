package com.feifei.feifeileave.domain.person.repository.persistence;

import com.feifei.feifeileave.domain.person.repository.facade.PersonRepository;
import com.feifei.feifeileave.domain.person.repository.mapper.PersonPOMapper;
import com.feifei.feifeileave.domain.person.repository.po.PersonPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 用户聚合仓储具体实现类
 * crud在此类下完成
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 22:08
 */
@Slf4j
@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    PersonPOMapper personPOMapper;

    @Override
    public PersonPO findLeaderByPersonId(Long leaderId) {
        return personPOMapper.getById(leaderId);
    }
}