package com.feifei.feifeileave.application;

/**
 * 鉴权应用服务接口
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 18:36
 */
public interface AuthApplicationService {
    /**
     * 登陆鉴权，分配token
     *
     * @author shixiongfei
     * @date 2020/4/30 6:38 下午
     * @param username 人员名称
     * @param password 登陆密码
     * @return
     */
    String login(String username, String password);
}