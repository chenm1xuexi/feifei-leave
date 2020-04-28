package com.feifei.feifeileave.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 请假单创建请求dto
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 19:02
 */
@Accessors(chain = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaveDTO {

    Long leaveId;

    /**
     * 申请人信息
     */
    @NotNull(message = "申请人信息不可为空")
    ApplicantDTO applicantDTO;

    /**
     * 审批人信息
     */
    @NotNull(message = "审批人信息不可为空")
    ApproverDTO approverDTO;

    /**
     * 请假类型
     */
    @NotBlank(message = "请假类型不可为空")
    String leaveType;

    /**
     * 当前审批意见
     */
    ApprovalInfoDTO currentApprovalInfoDTO;

    /**
     * 历史审批意见列表
     */
    List<ApprovalInfoDTO> historyApprovalInfoDTOList;

    /**
     * 请假开始时间
     */
    String startTime;

    /**
     * 请假结束时间
     */
    String endTime;

    /**
     * 请假总时长
     */
    Long duration;

    /**
     * 审批状态
     */
    String leaveStatus;
}