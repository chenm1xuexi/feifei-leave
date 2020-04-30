package com.feifei.feifeileave.interfaces.facade;

import com.feifei.feifeileave.application.LeaveApplicationService;
import com.feifei.feifeileave.domain.leave.entity.Leave;
import com.feifei.feifeileave.infrastructure.common.api.RichResult;
import com.feifei.feifeileave.interfaces.assembler.LeaveAssembler;
import com.feifei.feifeileave.interfaces.dto.LeaveDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.feifei.feifeileave.infrastructure.common.api.ApiConstants.LEAVE;

/**
 * 请假对外暴露的restful服务
 * 内部服务间调用统一走rpc调用
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 18:53
 */
@RestController
@RequestMapping(LEAVE)
@Slf4j
@AllArgsConstructor
public class LeaveApi {

    LeaveApplicationService leaveApplicationService;

    /**
     *  创建一个请假单
     *  接口层可用于参数的逻辑校验，具体的业务校验交由领域层去实现
     *  明确各层直接的职责，会使边界变得清晰（技术手段怎么实现看你自己，比如说可以采用hibernate的validate）
     *  这里只是做一个示例
     *
     * @author shixiongfei
     * @date 2020/4/28 3:49 下午
     * @param leaveDTO 请假单dto
     * @return
     */
    @PostMapping
    public RichResult<?> createLeaveInfo(@RequestBody @Validated LeaveDTO leaveDTO) {
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveApplicationService.createLeaveInfo(leave);
        return RichResult.ok();
    }

    /**
     *  编辑一个请假单，编辑的前提是请假单未进行过审批
     *
     * @author shixiongfei
     * @date 2020/4/28 4:06 下午
     * @param leaveDTO 请假单dto
     */
    @PutMapping
    public RichResult<?> updateLeaveInfo(LeaveDTO leaveDTO) {
        if (Objects.isNull(leaveDTO.getLeaveId()) || leaveDTO.getLeaveId() < 1) {
            return RichResult.badRequest("leaveId is null or is a illegal number， please check");
        }
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveApplicationService.updateLeaveInfo(leave);
        return RichResult.ok();
    }

    /**
     *
     * 审批人提交审批
     *
     * @author shixiongfei
     * @date 2020/4/28 7:26 下午
     * @param leaveDTO 请假单dto
     */
    @PostMapping("/submit")
    public RichResult<?> submitApproval(LeaveDTO leaveDTO) {
        if (Objects.isNull(leaveDTO.getLeaveId()) || leaveDTO.getLeaveId() < 1) {
            return RichResult.badRequest("leaveId is null or is a illegal number， please check");
        }
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveApplicationService.submitApproval(leave);
        return RichResult.ok();
    }

    /**
     *  获取请假单详情信息
     *
     * @author shixiongfei
     * @date 2020/4/28 10:15 下午
     * @param leaveId 请假单唯一标识
     * @return
     */
    @GetMapping("/{leaveId}")
    public RichResult<LeaveDTO> findById(@PathVariable Long leaveId) {
        return RichResult.ok(LeaveAssembler.toDTO(leaveApplicationService.findById(leaveId)));
    }

    /**
     * 根据申请人查询请假单列表
     *
     * @author shixiongfei
     * @date 2020/4/29 8:10 下午
     * @param applicantId
     * @return
     */
    @GetMapping("/query/applicant/{applicantId}")
    public RichResult<List<LeaveDTO>> listByApplicant(@PathVariable String applicantId) {
        List<Leave> leaveList = leaveApplicationService.listByApplicant(applicantId);
        List<LeaveDTO> list = leaveList.stream().map(LeaveAssembler::toDTO).collect(Collectors.toList());
        return RichResult.ok(list);
    }

    /**
     * 通过审批人获取待审批请假单列表（待办任务）
     *
     * @author shixiongfei
     * @date 2020/4/30 5:30 下午
     * @param approverId 审批人id
     * @return
     */
    @GetMapping("/query/approver/{approverId}")
    public RichResult<List<LeaveDTO>> listByApprover(@PathVariable String approverId) {
        List<Leave> leaveList = leaveApplicationService.listByApprover(approverId);
        List<LeaveDTO> list = leaveList.stream().map(LeaveAssembler::toDTO).collect(Collectors.toList());
        return RichResult.ok(list);
    }
}