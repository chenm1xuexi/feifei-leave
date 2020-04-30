package com.feifei.feifeileave.application.impl;

import com.feifei.feifeileave.application.PersonApplicationService;
import com.feifei.feifeileave.domain.person.entity.Person;
import com.feifei.feifeileave.domain.person.service.PersonDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 人员管理应用服务具体实现类
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 17:45
 */
@Slf4j
@Service
@AllArgsConstructor
public class PersonApplicationServiceImpl implements PersonApplicationService {

    PersonDomainService personDomainService;

    @Override
    public void create(Person person) {
        personDomainService.create(person);
    }
}