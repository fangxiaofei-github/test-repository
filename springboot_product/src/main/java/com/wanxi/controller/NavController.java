package com.wanxi.controller;

import com.wanxi.pojo.EsNav;
import com.wanxi.pojo.Nav;
import com.wanxi.service.EsNavService;
import com.wanxi.service.NavService;
import com.wanxi.service.impl.EsNavServiceImpl;
import com.wanxi.vo.NavVO;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 导航栏信息的控制层
 * @author: fxf
 * @date: 2022/8/26 22:29
 */
@Api(tags = "导航栏信息的控制层模块")
@RestController
public class NavController {

    @Autowired
    private NavService navService;

    @Autowired
    private EsNavService esNavService;

    /**
     * 根据查询条件和分页参数查询导航栏信息
     */
    @ApiOperation("根据查询条件和分页参数查询导航栏信息")
    @GetMapping("/navInfo")
    @PreAuthorize("hasAuthority('nav:get')")
    public ResultInfo selectByConditionAndPage(NavVO navVO){
        // 调用业务层的相关方法
        ResultInfo info = navService.selectByConditionAndPage(navVO);
        return info;
    }

    /**
     * 添加导航栏信息
     * @param nav
     * @return
     */
    @ApiOperation("添加导航栏信息")
    @PostMapping("/navInfoAdd")
    @PreAuthorize("hasAuthority('nav:add')")
    public ResultInfo addNav(@RequestBody Nav nav){
        //调用业务的新增方法
        ResultInfo info = navService.addNav(nav);
        return info;
    }

    /**
     * 删除导航栏信息(实际上是改变状态)
     * @param ids
     * @return
     */
    @ApiOperation("删除导航栏信息(实际上是改变状态)")
    @GetMapping("/navInfoDel")
    @PreAuthorize("hasAuthority('nav:delete')")
    public ResultInfo delNav(String ids){
        // 拆分ids
        String[] idArr = ids.split(",");
        //调用业务层的相关的方法
        ResultInfo info = navService.updateNavStatus(idArr);
        return info;
    }

    /**
     * 修改导航栏信息
     * @param nav
     * @return
     */
    @ApiOperation("修改导航栏信息")
    @PostMapping("/navInfoEdit")
    @PreAuthorize("hasAuthority('nav:update')")
    public ResultInfo editNav(@RequestBody Nav nav){
        //调用业务的修改方法
        ResultInfo info = navService.editNav(nav);
        return info;
    }


    /**
     * ES --根据查询条件和分页参数查询导航栏信息
     */
    @GetMapping("/esNavInfo")
    @PreAuthorize("hasAuthority('nav:get')")
    public ResultInfo selectByParam(EsNav esNav){
        System.out.println(esNav);
        // 调用业务层的相关方法
        ResultInfo info = esNavService.selectByConditionAndPage(esNav);
        System.out.println(info.getData());
        return info;
    }

}
