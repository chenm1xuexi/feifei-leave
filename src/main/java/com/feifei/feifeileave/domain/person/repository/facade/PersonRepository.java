package com.feifei.feifeileave.domain.person.repository.facade;

import com.feifei.feifeileave.domain.person.repository.po.PersonPO;

/**
 * 用户聚合仓储接口类
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 22:07
 */

public interface PersonRepository {

    PersonPO findLeaderByPersonId(Long leaderId);
}
