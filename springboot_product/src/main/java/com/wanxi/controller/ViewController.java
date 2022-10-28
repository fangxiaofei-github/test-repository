package com.wanxi.controller;

import com.wanxi.vo.LoginVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 视图返回
 * @author: fxf
 * @date: 2022/8/29 16:38
 */
@Api(tags = "以前模块不用管")
@Controller
public class ViewController {

    /**
     * 实现自动登录接口
     * @param loginVO
     * @return
     */
    @GetMapping("/autoLogin")
    public ModelAndView autoLogin(LoginVO loginVO){
        ModelAndView mv = new ModelAndView();
        mv.addObject("username",loginVO.getUsername());
        mv.addObject("password",loginVO.getPassword());
        mv.addObject("remember",loginVO.getRemember());
        mv.setViewName("redirect:/login");
        return mv;
    }


    /**
     * 返回登录页面视图
     * @return
     */
    @RequestMapping("/loginView")
    public String returnLogin(){
        return "/login.html";
    }
}
