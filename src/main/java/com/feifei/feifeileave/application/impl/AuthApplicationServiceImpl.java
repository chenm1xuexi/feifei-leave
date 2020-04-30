package com.feifei.feifeileave.application.impl;

import com.feifei.feifeileave.application.AuthApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 鉴权应用服务具体实现类
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 18:36
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthApplicationServiceImpl implements AuthApplicationService {

    // rpc调用鉴权服务中心, 这里不做演示
    // AuthService authService;

    @Override
    public String login(String username, String password) {
        // return authService.login(username, password);
        return null;
    }
}