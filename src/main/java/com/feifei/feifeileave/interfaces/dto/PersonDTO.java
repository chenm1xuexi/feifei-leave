package com.feifei.feifeileave.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 人员信息dto
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 17:49
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class PersonDTO {

    /**
     * 用户唯一标识
     */
    Long personId;

    /**
     * 人员名称
     */
    @NotBlank(message = "人员名称不可为空")
    String personName;

    /**
     * 角色级别
     */
    Integer roleLevel;

    /**
     * 上级关系
     */
    List<RelationShipDTO> relationShipDTOS;

    /**
     * 部门id
     */
    @NotNull(message = "部门不可为空")
    Long departmentId;

    /**
     * 人员类型 内部人员还是外部人员
     */
    String personType;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 更新时间
     */
    Date updateTime;

    /**
     * 人员状态，是否可用 'y'代表可用 'n'代表不可用
     */
    String personStatus;
}