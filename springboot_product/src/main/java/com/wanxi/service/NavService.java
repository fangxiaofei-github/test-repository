package com.wanxi.service;

import com.wanxi.pojo.Nav;
import com.wanxi.vo.NavVO;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 导航栏信息的业务层
 * @author: fxf
 * @date: 2022/8/26 22:34
 */
public interface NavService {

    /**
     * 根据查询条件和分页参数查询导航栏信息
     * @param navVO
     * @return
     */
    ResultInfo selectByConditionAndPage(NavVO navVO);

    /**
     * 添加导航栏信息
     * @param nav
     * @return
     */
    ResultInfo addNav(Nav nav);

    /**
     * 改变导航栏信息的状态
     * @param idArr
     * @return
     */
    ResultInfo updateNavStatus(String[] idArr);


    /**
     * 修改导航栏信息
     * @param nav
     * @return
     */
    ResultInfo editNav(Nav nav);
}
