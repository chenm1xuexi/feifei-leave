package com.feifei.feifeileave.application;

import com.feifei.feifeileave.domain.person.entity.Person;

/**
 * 人员管理应用服务
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 17:45
 */
public interface PersonApplicationService {

    /**
     * 创建人员
     *
     * @author shixiongfei
     * @date 2020/4/30 6:07 下午
     * @param person 人员实体
     * @return
     */
    void create(Person person);
}