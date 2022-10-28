package com.wanxi.service.impl;

import com.wanxi.mapper.MenuMapper;
import com.wanxi.pojo.Menu;
import com.wanxi.service.MenuService;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.zip.Inflater;

/**
 * @description:
 * @author: fxf
 * @date: 2022/9/23 9:27
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 添加权限信息
     * @param menu
     * @return
     */
    @Override
    public ResultInfo addMenu(Menu menu) {
        ResultInfo info = new ResultInfo();
        // 非法参数校验
        String menuName = menu.getMenuName();
        if (!StringUtils.hasText(menuName)){
            info.setCode(0);
            info.setMsg("权限名称不能为空");
            return info;
        }
        String perms = menu.getPerms();
        if (!StringUtils.hasText(perms)){
            info.setCode(0);
            info.setMsg("权限符不能为空");
            return info;
        }
        // 其他参数
        // 查询权限符是否已经存在
        // 调用持久层的新增方法
        int count = menuMapper.addMenu(menu);
        if (count<1){
            info.setMsg("新增失败");
            return info;
        }
        info.setMsg("新增成功");
        return info;
    }

    /**
     * 删除权限信息（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public ResultInfo delMenu(Integer id) {
        ResultInfo info = new ResultInfo();
        // 非法参数校验
        if (id == null){
            info.setMsg("权限的id为空");
            return info;
        }
        int count = menuMapper.updateStatus(id);
        if (count < 1){
            info.setMsg(" 逻辑删除失败");
            return info;
        }
        info.setMsg("逻辑删除成功");
        return info;
    }

    /**
     * 修改权限信息
     * @param menu
     * @return
     */
    @Override
    public ResultInfo updateMenu(Menu menu) {
        ResultInfo info = new ResultInfo();
        int count = menuMapper.updateMenu(menu);
        if (count < 1){
            info.setMsg(" 修改权限信息失败");
            return info;
        }
        info.setMsg("修改权限信息成功");
        return info;
    }

    /**
     * 查询所有信息
     * @return
     */
    @Override
    public ResultInfo selectAll() {
        ResultInfo info = new ResultInfo();
        List<Menu> menuList = menuMapper.selectAll();
        if (menuList == null){
            info.setMsg(" 查询权限信息失败");
            return info;
        }
        info.setData(menuList);
        info.setMsg("查询权限信息成功");
        return info;
    }



}
