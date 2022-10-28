package com.wanxi.service;

import com.wanxi.vo.LoginVO;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 登录相关的业务层
 * @author: fxf
 * @date: 2022/8/25 18:19
 */
public interface LoginService {

    /**
     * 根据用户名和密码去登录
     */
    ResultInfo login(String username, String password);


    /**
     * security需要的自定义登录业务层方法
     * @param loginVO
     * @return
     */
    ResultInfo userLogin(LoginVO loginVO);


    /**
     * security 退出登录
     */
    ResultInfo userLogout();
}
