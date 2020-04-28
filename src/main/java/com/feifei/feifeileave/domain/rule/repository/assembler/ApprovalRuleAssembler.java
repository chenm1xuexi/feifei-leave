package com.feifei.feifeileave.domain.rule.repository.assembler;

import com.feifei.feifeileave.domain.rule.entity.ApprovalRule;
import com.feifei.feifeileave.domain.rule.repository.po.ApprovalRulePO;
import org.springframework.beans.BeanUtils;

/**
 * po do转换器
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 21:21
 */
public interface ApprovalRuleAssembler {

    static ApprovalRulePO toPO(ApprovalRule approvalRule) {
        ApprovalRulePO po = new ApprovalRulePO();
        BeanUtils.copyProperties(approvalRule, po);
        return po;
    }
}
