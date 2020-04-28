package com.feifei.feifeileave.domain.rule.service;

import com.feifei.feifeileave.domain.rule.entity.ApprovalRule;
import com.feifei.feifeileave.domain.rule.repository.facade.ApprovalRuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 审批规则领域服务, 领域服务一般不需要编写接口，一个聚合中一般只会存在一个领域服务
 *  用于处理审批规则本身实体无法处理的业务场景
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:55
 */
@Slf4j
@Service
@AllArgsConstructor
public class ApprovalRuleDomainService {

    ApprovalRuleRepository approvalRuleRepository;

    /**
     * 根据人员类型 + 请假类型 + 请假天数来确定审批规则
     *
     * @author shixiongfei
     * @date 2020/4/21 8:18 下午
     * @param personType 人员类型
     * @param leaveType 请假类型
     * @param duration 请假天数
     * @return int
     */
    public int getLeaderMaxLevel(String personType, String leaveType, Long duration) {
        ApprovalRule approvalRule = new ApprovalRule();
        approvalRule.setPersonType(personType)
                .setLeaveType(leaveType)
                .setDuration(duration);
        return approvalRuleRepository.getLeaderMaxLevel(approvalRule);
    }
}