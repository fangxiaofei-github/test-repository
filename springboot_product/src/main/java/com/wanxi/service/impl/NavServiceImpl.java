package com.wanxi.service.impl;

import com.wanxi.mapper.NavMapper;
import com.wanxi.pojo.Nav;
import com.wanxi.service.NavService;
import com.wanxi.vo.NavVO;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 导航栏信息的业务层
 * @author: fxf
 * @date: 2022/8/26 22:36
 */
@Service
public class NavServiceImpl implements NavService {

    @Autowired
    private NavMapper navMapper;

    /**
     * 根据查询条件和分页参数查询导航栏信息
     * @param navVO
     * @return
     */
    @Override
    public ResultInfo selectByConditionAndPage(NavVO navVO) {
        ResultInfo info = new ResultInfo();
        // 计算出分页查询的起始下标
        Integer pageIndex = Integer.parseInt(navVO.getPage());
        Integer offset = Integer.parseInt(navVO.getLimit());
        Integer index = (pageIndex - 1)*offset;
        navVO.setCurrentIndex(index);
        navVO.setCurrentCount(offset);
        // 调用持久层的相关方法
        List<Nav> navModels =  navMapper.selectByConditionAndPage(navVO);
        if (navModels == null || navModels.size() ==0){
            info.setMsg("没有查询到信息！");
            return info;
        }
        int count = navMapper.findNavCount("nav");
        info.setMsg("查询到信息！");
        info.setCode(0);
        info.setCount(count);
        info.setData(navModels);
        return info;
    }

    /**
     * 添加导航栏信息
     * @param nav
     * @return
     */
    @Override
    public ResultInfo addNav(Nav nav) {
        ResultInfo info = new ResultInfo();
        nav.setCreateTime(new Date());
        nav.setUpdateTime(new Date());
        //调用持久层的新增方法
        int row = navMapper.addNav(nav);
        if (row < 1){
            info.setMsg("添加失败！");
            return info;
        }
        info.setMsg("添加成功！");
        return info;
    }

    /**
     * 改变导航栏信息的状态
     * @param idArr
     * @return
     */
    @Override
    public ResultInfo updateNavStatus(String[] idArr) {
        ResultInfo info = new ResultInfo();
        for (int i = 0; i < idArr.length; i++) {
            navMapper.updateNavStatus(Integer.parseInt(idArr[i]));
        }
        info.setMsg("已经把状态该为已删除状态！");
        return info;
    }


    /**
     * 修改导航栏信息
     * @param nav
     * @return
     */
    @Override
    public ResultInfo editNav(Nav nav) {
        ResultInfo info = new ResultInfo();
        nav.setUpdateTime(new Date());
        // 调用持久层的修改方法
        int row = navMapper.editNav(nav);
        if (row < 1){
            info.setMsg("修改失败！");
            return info;
        }
        info.setMsg("修改成功！");
        return info;
    }
}
