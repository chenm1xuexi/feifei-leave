package com.feifei.feifeileave.domain.leave.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feifei.feifeileave.domain.leave.repository.po.LeavePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.feifei.feifeileave.domain.leave.repository.po.LeavePO.COL_APPLICANT_ID;
import static com.feifei.feifeileave.domain.leave.repository.po.LeavePO.COL_APPROVER_ID;

@Mapper
public interface LeaveMapper extends BaseMapper<LeavePO> {

    default List<LeavePO> listByApplicantId(String applicantId) {
        QueryWrapper<LeavePO> wrapper = new QueryWrapper<>();
        wrapper.eq(COL_APPLICANT_ID, applicantId);
        return selectList(wrapper);
    }

    default List<LeavePO> listByApproverId(String approverId) {
        QueryWrapper<LeavePO> wrapper = new QueryWrapper<>();
        wrapper.eq(COL_APPROVER_ID, approverId);
        return selectList(wrapper);
    }
}