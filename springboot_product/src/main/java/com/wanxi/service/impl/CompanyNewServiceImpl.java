package com.wanxi.service.impl;

import com.wanxi.mapper.CompanyNewMapper;
import com.wanxi.pojo.CompanyNew;
import com.wanxi.service.CompanyNewService;
import com.wanxi.vo.CompanyNewVO;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: fxf
 * @date: 2022/8/26 11:39
 */
@Service
public class CompanyNewServiceImpl implements CompanyNewService {

    @Autowired
    private CompanyNewMapper companyNewMapper;


    /**
     * 根据参数和分页条件查询信息
     * @param companyNewVO
     * @return
     */
    @Override
    public ResultInfo selectByConditionAndPage(CompanyNewVO companyNewVO) {
        ResultInfo info = new ResultInfo();
        //得到当前页的下标
        Integer pageNum = Integer.parseInt(companyNewVO.getPage());
        Integer currentCount = Integer.parseInt(companyNewVO.getLimit());
        Integer currentIndex = (pageNum-1)*currentCount;
        companyNewVO.setCurrentCount(currentCount);
        companyNewVO.setCurrentIndex(currentIndex);

        //通过条件查询
        List<CompanyNew> companyNewModelList = companyNewMapper.selectByConditionAndPage(companyNewVO);
        if (companyNewModelList == null ||companyNewModelList.size() < 1){
            info.setMsg("没有查询到信息！");
            return info;
        }
        //获取表中共有几条数据
        int count = companyNewMapper.findCompanyNewCount();
        info.setMsg("查询到信息！");
        info.setData(companyNewModelList);
        info.setCode(0);
        info.setCount(count);
        return info;
    }

    /**
     * 添加公司新闻信息
     * @param companyNew
     * @return
     */
    @Override
    public ResultInfo addCompanyNew(CompanyNew companyNew) {
        ResultInfo info = new ResultInfo();
        companyNew.setCreateTime(new Date());
        companyNew.setUpdateTime(new Date());
        // 调用业务层相关新增方法
        int row = companyNewMapper.addCompanyNew(companyNew);
        if (row>0){
            info.setMsg("添加成功！");
            return info;
        }
        info.setMsg("添加失败！");
        return info;
    }

    /**
     * 新闻信息删除操作（不是真正删除而是逻辑删除）
     * @param ids
     * @return
     */
    @Override
    public ResultInfo updateCompanyNewStatus(String[] ids) {
        ResultInfo info = new ResultInfo();
        // 非法参数校验
        if (ids == null || "".equals(ids)){
            info.setMsg("id不能为空！");
            return info;
        }
        // 调用持久层相关的方法：把status改为0
        for (int i = 0; i < ids.length; i++) {
            companyNewMapper.updateCompanyNewStatus(Integer.parseInt(ids[i]));
        }
        info.setMsg("删除成功！");
        return info;
    }

    @Override
    public ResultInfo editCompanyNew(CompanyNew companyNew) {
        ResultInfo info = new ResultInfo();
        companyNew.setUpdateTime(new Date());
        // 调用持久层的修改方法
        int row = companyNewMapper.editCompanyNew(companyNew);
        if (row < 1){
            info.setMsg("修改失败！");
            return info;
        }
        info.setMsg("修改成功！");
        return info;
    }
}
