package com.feifei.feifeileave.domain.rule.repository.facade;

import com.feifei.feifeileave.domain.rule.entity.ApprovalRule;

/**
 * 审批规则仓储接口类，具体实现放在聚合内部，
 * 为了后续拆分统一迁移，但是切记领域对象不可直接调用
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:56
 */
public interface ApprovalRuleRepository {

    /**
     * 获取审批级别
     *
     * @author shixiongfei
     * @date 2020/4/21 8:20 下午
     * @param rule 审批规则
     * @return 获取审批规则
     */
    int getLeaderMaxLevel(ApprovalRule rule);
}