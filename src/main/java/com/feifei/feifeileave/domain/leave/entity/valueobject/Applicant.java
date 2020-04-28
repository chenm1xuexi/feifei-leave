package com.feifei.feifeileave.domain.leave.entity.valueobject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 申请人值对象
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 14:51
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Applicant {

    /**
     * 人员id
     */
    Long personId;

    /**
     * 人员名称
     */
    String personName;

    /**
     * 人员类型
     */
    String personType;


}