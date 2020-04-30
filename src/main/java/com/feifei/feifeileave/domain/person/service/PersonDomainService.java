package com.feifei.feifeileave.domain.person.service;

import com.feifei.feifeileave.domain.person.entity.Person;
import com.feifei.feifeileave.domain.person.repository.facade.PersonRepository;
import com.feifei.feifeileave.domain.person.repository.mapper.PersonPOMapper;
import com.feifei.feifeileave.domain.person.repository.po.PersonPO;
import com.feifei.feifeileave.infrastructure.LeaveException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 人员聚合 领域服务
 *
 * 如果领域服务想直接调用mapper也可以，
 * 这里只是严格按照规范来进行执行，具体视自身习惯而言
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 21:58
 */
@Slf4j
@Service
@AllArgsConstructor
public class PersonDomainService {

    PersonRepository personRepository;

    PersonFactory personFactory;

    PersonPOMapper personMapper;

    /**
     * 通过申请人和最大审批规则级别来获取下一审批人
     *
     * @author shixiongfei
     * @date 2020/4/21 10:26 下午
     * @param personId 申请人id
     * @param leaderMaxLevel 审批规则最大级别
     * @return com.feifei.feifeileave.domain.person.entity.Person
     */
    public Person findFirstApprover(Long personId, int leaderMaxLevel) {
        // 1. 获取当前有效申请人的相关信息
        PersonPO po = personRepository.findLeaderByPersonId(personId);
        if (Objects.isNull(po)) {
            throw new RuntimeException(String.format("person is not exists, personId {%s}", personId));
        }
        // 获取申请人的leader的角色等级
        PersonPO leader = personRepository.findLeaderByPersonId(po.getLeaderId());
        if (Objects.isNull(leader)) {
            throw new RuntimeException(String.format("leader is not exists, leaderId {%s}", po.getLeaderId()));
        }
        int roleLevel = leader.getRoleLevel();
        if (roleLevel > leaderMaxLevel) {
            return null;
        } else {
            return personFactory.createPerson(leader);
        }
    }

    /**
     *
     * 获取当前审批人的领导人员信息，如果不存在上级领导或者领导的level大于请假规则的最大等级则返回null,否则返回leader信息
     *
     * @author shixiongfei
     * @date 2020/4/28 8:23 下午
     * @param currentApproverId 当前人员id
     * @param leaderMaxLevel 最高领导等级
     * @return com.feifei.feifeileave.domain.person.entity.Person
     */
    public Person findNextApprover(Long currentApproverId, Integer leaderMaxLevel) {
        PersonPO leader = personRepository.findLeaderByPersonId(currentApproverId);
        if (Objects.isNull(leader) || leader.getRoleLevel() > leaderMaxLevel) {
            return null;
        }
        return personFactory.createPerson(leader);
    }

    /**
     * 创建一个人员信息，前提是当前人员不存在
     *
     * @author shixiongfei
     * @date 2020/4/30 6:09 下午
     * @param person 人员实体
     * @return
     */
    public void create(Person person) {
        // 查询是否存在同名的人员
        PersonPO po = personMapper.getByName(person.getPersonName());
        if (Objects.isNull(po)) {
            throw new LeaveException("person already exists, please check");
        }
        // TODO 判断leader是否为空，不为空则判断是否存在此人员信息
        // 新增前初始化相关参数
        person.create();
        personMapper.insert(personFactory.createPersonPO(person));
    }
}