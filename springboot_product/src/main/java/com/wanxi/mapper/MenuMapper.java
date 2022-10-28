package com.wanxi.mapper;

import com.wanxi.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 权限信息的持久层
 * @author: fxf
 * @date: 2022/9/23 9:37
 */
@Mapper
public interface MenuMapper {

    /**
     * 权限信息的新增
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 修改某条权限信息的状态
     * @param id
     * @return
     */
    int updateStatus(Integer id);

    /**
     * 修改权限信息
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 查询所有权限信息
     * @return
     */
    List<Menu> selectAll();
}
