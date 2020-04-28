package com.feifei.feifeileave.domain.rule.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feifei.feifeileave.domain.rule.repository.po.ApprovalRulePO;
import org.apache.ibatis.annotations.Mapper;

import static com.feifei.feifeileave.domain.rule.repository.po.ApprovalRulePO.*;

@Mapper
public interface ApprovalRuleMapper extends BaseMapper<ApprovalRulePO> {

   default ApprovalRulePO findRule(String leaveType, String personType, Long duration) {
       QueryWrapper<ApprovalRulePO> wrapper = new QueryWrapper<>();
       wrapper.eq(COL_LEAVE_TYPE, leaveType)
               .eq(COL_PERSON_TYPE, personType)
               .eq(COL_DURATION, duration);
       return selectOne(wrapper);
   }
}