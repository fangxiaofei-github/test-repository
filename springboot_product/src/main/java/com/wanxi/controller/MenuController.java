package com.wanxi.controller;

import com.wanxi.pojo.Menu;
import com.wanxi.service.MenuService;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 权限信息的控制层
 * @author: fxf
 * @date: 2022/9/23 9:26
 */
@Api(tags = "权限信息的控制层模块")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 新增方法
     * @param menu
     * @return
     */
    @ApiOperation("新增权限信息接口")
    @RequestMapping("/addMenu")
    public ResultInfo addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    /**
     * 删除方法（逻辑删除）
     * @param id
     * @return
     */
    @ApiOperation("删除权限信息接口")
    @RequestMapping("/delMenu")
    public ResultInfo delMenu(Integer id){
        return menuService.delMenu(id);
    }

    /**
     * 修改权限方法
     * @param menu
     * @return
     */
    @ApiOperation("修改权限信息接口")
    @RequestMapping("/updateMenu")
    public ResultInfo updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    /**
     * 查询所有信息
     * @return
     */
    @ApiOperation("查询权限所有信息的接口")
    @RequestMapping("/findAll")
    public ResultInfo selectAll(){
        return menuService.selectAll();
    }

}
