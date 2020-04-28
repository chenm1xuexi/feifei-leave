package com.feifei.feifeileave.infrastructure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * 请假系统异常类
 *
 * @Author: shixiongfei
 * @Date: 2020/4/28 19:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LeaveException extends RuntimeException implements Supplier<String> {

    private int code;

    public LeaveException(String message) {
        super(message);
        this.code = 11111;
    }

    public LeaveException(int code, String message) {
        super(message);
        this.code = code;
    }

    public LeaveException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public String get() {
        return super.getMessage();
    }
}