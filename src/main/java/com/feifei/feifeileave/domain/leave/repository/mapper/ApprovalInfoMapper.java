package com.feifei.feifeileave.domain.leave.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feifei.feifeileave.domain.leave.repository.po.ApprovalInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.feifei.feifeileave.domain.leave.repository.po.ApprovalInfoPO.COL_LEAVE_ID;

@Mapper
public interface ApprovalInfoMapper extends BaseMapper<ApprovalInfoPO> {

    /**
     * 通过请假唯一标识来获取历史审批意见列表
     *
     * @author shixiongfei
     * @date 2020/4/28 7:50 下午
     * @param leaveId 请假单唯一标识
     * @return
     */
    default List<ApprovalInfoPO> listApprovalInfoByLeaveId(Long leaveId) {
        QueryWrapper<ApprovalInfoPO> wrapper = new QueryWrapper<>();
        wrapper.eq(COL_LEAVE_ID, leaveId);
        return selectList(wrapper);
    }

    /**
     * 批量新增审批意见
     *
     * @author shixiongfei
     * @date 2020/4/28 7:57 下午
     * @param pos
     * @return
     */
    default void saveApprovalInfos(List<ApprovalInfoPO> pos) {
        pos.forEach(this::insert);
    }
}