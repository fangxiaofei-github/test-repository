package com.wanxi.controller;

import com.wanxi.service.LoginService;
import com.wanxi.vo.LoginVO;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @description: 登录相关的控制层
 * @author: fxf
 * @date: 2022/8/25 12:43
 */
@Api(tags = "登录相关的控制层模块")
@ResponseBody
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    //@RequestMapping("/login")
    //public ResultInfo login(@RequestBody LoginVO loginVO, HttpServletResponse response, HttpSession session){
    //
    //    String username = loginVO.getUsername();
    //    String password = loginVO.getPassword();
    //    String remember = loginVO.getRemember();
    //
    //    //调用业务层的登录相关的方法
    //    ResultInfo resultInfo = loginService.login(username, password);
    //    System.out.println(resultInfo.getData());
    //
    //    // 先判断是否登录成功
    //    if (resultInfo.getCode() == 2){
    //
    //        ////登录成功了，把用户信息存储到session中去
    //        session.setAttribute("user", resultInfo.getData());
    //
    //        // 然后再判断“记住密码”是否勾选（若前端没有规定传递的值则默认为on）
    //        if ("on".equals(remember)){
    //            //勾选上了，就把用户信息存储到cookie中去返回给前端，让它实现自动填充
    //            Cookie cookie = new Cookie("user",username + "-" + password);
    //            //设置失效时间，不设置默认setMaxAge(-1)即会话级别的，关闭游览器就失效
    //            cookie.setMaxAge(7*24*60*60);
    //            response.addCookie(cookie);
    //        }else { //若没有勾选上，就让cookie立即失效
    //            Cookie cookie = new Cookie("user", null);
    //            cookie.setMaxAge(0);
    //            response.addCookie(cookie);
    //        }
    //    }
    //
    //    return resultInfo;
    //}



    /**
     * security需要的自定义登录接口，并且要把这个登录接口放行
     */
    @ApiOperation("登录接口")
    @PostMapping("/user/login")
    public ResultInfo userLogin( @RequestBody LoginVO loginVO ){
        return loginService.userLogin(loginVO);
    }


    /**
     * security 退出登录
     */
    @ApiOperation("退出接口")
    @GetMapping("/user/logout")
    public ResultInfo userLogout(){
        return loginService.userLogout();
    }



}
