package com.feifei.feifeileave.infrastructure.util;

import java.util.UUID;

/**
 * id生成器工具类
 *
 * @Author: shixiongfei
 * @Date: 2020/4/21 17:01
 */
public interface IdGenerator {

    static String nextId(){
        return UUID.randomUUID().toString();
    }
}