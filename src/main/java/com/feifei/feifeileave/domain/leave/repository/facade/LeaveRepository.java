package com.feifei.feifeileave.domain.leave.repository.facade;

import com.feifei.feifeileave.domain.leave.repository.po.LeavePO;

/**
 * 请假单仓储接口，本来具体实现交由基础设施层去完成，
 * 但是由于数据库一般不会进行变更，因此具体实现也放置到领域层中
 * 个人认为也方便后续微服务拆分，只要领域实体尽量避免调用仓储服务即可
 *
 * 当单个mapper无法满足时则在此仓储中完成
 *
 * @author shixiongfei
 * @date 2020/4/21 5:13 下午
 */
public interface LeaveRepository {

    /**
     * 创建请假单
     *
     * @author shixiongfei
     * @date 2020/4/28 8:00 下午
     * @param leavePO 请假单po
     * @return
     */
    void save(LeavePO leavePO);

    /**
     * 审批提交时，更新请假单
     *
     * @author shixiongfei
     * @date 2020/4/28 9:12 下午
     * @param leavePO 请假单po
     * @return
     */
    void updateApproval(LeavePO leavePO);

    /**
     *  获取请假单信息
     *
     * @author shixiongfei
     * @date 2020/4/28 9:51 下午
     * @param leaveId 唯一标识
     * @return
     */
    LeavePO findByLeaveId(Long leaveId);
}