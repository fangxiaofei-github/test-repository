package com.wanxi.service;

import com.wanxi.pojo.Menu;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 权限信息的业务层
 * @author: fxf
 * @date: 2022/9/23 9:27
 */
public interface MenuService {

    /**
     * 新增权限信息
     * @param menu
     * @return
     */
    ResultInfo addMenu(Menu menu);

    /**
     * 删除方法（逻辑删除）
     * @param id
     * @return
     */
    ResultInfo delMenu(Integer id);

    /**
     * 修改权限信息
     * @param menu
     * @return
     */
    ResultInfo updateMenu(Menu menu);

    /**
     * 查询所有信息
     * @return
     */
    ResultInfo selectAll();
}
