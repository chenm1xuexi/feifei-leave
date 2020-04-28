package com.feifei.feifeileave.domain.leave.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feifei.feifeileave.domain.leave.repository.po.LeavePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveMapper extends BaseMapper<LeavePO> {
}