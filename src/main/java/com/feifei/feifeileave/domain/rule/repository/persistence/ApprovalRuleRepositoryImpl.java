package com.feifei.feifeileave.domain.rule.repository.persistence;

import com.feifei.feifeileave.domain.rule.entity.ApprovalRule;
import com.feifei.feifeileave.domain.rule.repository.facade.ApprovalRuleRepository;
import com.feifei.feifeileave.domain.rule.repository.mapper.ApprovalRuleMapper;
import com.feifei.feifeileave.domain.rule.repository.po.ApprovalRulePO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 审批规则仓储具体实现类，
 * 不包含如何业务逻辑，只是简单的用来crud
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:58
 */
@Slf4j
@Repository
@AllArgsConstructor
public class ApprovalRuleRepositoryImpl implements ApprovalRuleRepository {

    ApprovalRuleMapper approvalRuleMapper;

    @Override
    public int getLeaderMaxLevel(ApprovalRule rule) {
        ApprovalRulePO po = approvalRuleMapper.findRule(rule.getLeaveType(), rule.getPersonType(), rule.getDuration());
        return po.getMaxLeaderLevel();
    }
}