package com.wanxi.service;

import com.wanxi.pojo.EsNav;
import com.wanxi.vo.ResultInfo;

/**
 * @description:
 * @author: fxf
 * @date: 2022/10/8 14:58
 */
public interface EsNavService {

    /**
     * 从数据库中导入所有商品到ES
     */
    void findAllToES();

    /**
     * 根据查询条件和分页参数查询信息
     */
    ResultInfo selectByConditionAndPage(EsNav esNav);

}
