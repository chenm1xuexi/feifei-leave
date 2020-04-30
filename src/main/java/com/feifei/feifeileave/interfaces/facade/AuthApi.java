package com.feifei.feifeileave.interfaces.facade;

import com.feifei.feifeileave.application.AuthApplicationService;
import com.feifei.feifeileave.infrastructure.common.api.RichResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.feifei.feifeileave.infrastructure.common.api.ApiConstants.AUTH;

/**
 * TODO description
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 18:24
 */
@RestController
@RequestMapping(AUTH)
@Slf4j
@AllArgsConstructor
public class AuthApi {

    AuthApplicationService applicationService;

    @PostMapping("/login")
    public RichResult<String> login(@RequestParam String username, @RequestParam String password) {
        return RichResult.ok(applicationService.login(username, password));
    }
}
