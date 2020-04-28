package com.feifei.feifeileave.infrastructure.common.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.httpclient.HttpStatus;

import java.util.Collections;

/**
 * 统一restful响应实体类
 * 构造器设为私有是希望开发人员能够按照提供的模版方法来实现
 *
 * 如需拓展，请自定义添加
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 18:32
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RichResult<T> {

    public static final String SUCCESS = "SUCCESS";

    /**
     * 状态码
     */
    int code;

    /**
     * 响应消息
     */
    String msg;

    /**
     * 响应内容
     */
    T data;

    public static <T> RichResult<T> ok(T data) {
        return new RichResult<>(HttpStatus.SC_OK, SUCCESS, data);
    }

    public static RichResult<?> ok() {
        return new RichResult<>(HttpStatus.SC_OK, SUCCESS, Collections.emptyList());
    }

    public static RichResult<?> badRequest(String errorMsg) {
        return new RichResult<>(HttpStatus.SC_BAD_REQUEST, errorMsg, Collections.emptyList());
    }

    public static RichResult<?> error(String errorMsg) {
        return new RichResult<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, errorMsg, Collections.emptyList());
    }
}