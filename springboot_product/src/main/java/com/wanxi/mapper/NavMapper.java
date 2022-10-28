package com.wanxi.mapper;

import com.wanxi.pojo.EsNav;
import com.wanxi.pojo.Nav;
import com.wanxi.vo.NavVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 导航栏信息的持久层
 * @author: fxf
 * @date: 2022/8/26 22:37
 */
@Mapper
public interface NavMapper {

    /**
     * 根据查询条件和分页参数查询导航栏信息
     * @param navVO
     * @return
     */
    List<Nav> selectByConditionAndPage(NavVO navVO);

    /**
     * 查询总共有多少条记录
     * @param nav
     * @return
     */
    int findNavCount(String nav);

    /**
     * 添加导航栏信息
     * @param nav
     * @return
     */
    int addNav(Nav nav);

    /**
     * 改变导航栏信息的状态
     * @return
     */
    void updateNavStatus(int id);

    /**
     * 修改导航栏信息
     * @param nav
     * @return
     */
    int editNav(Nav nav);

    /**
     * 查询使用导航信息
     */
    List<EsNav> findAll();
}
