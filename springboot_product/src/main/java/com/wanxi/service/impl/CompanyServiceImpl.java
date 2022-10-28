package com.wanxi.service.impl;

import com.wanxi.mapper.CompanyMapper;
import com.wanxi.pojo.Company;
import com.wanxi.service.CompanyService;
import com.wanxi.vo.CompanyVO;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 公司信息相关的业务层
 * @author: fxf
 * @date: 2022/8/25 23:17
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 根据查询参数和分页条件查询公司信息
     */
    @Override
    public ResultInfo selectCompanyByLimit(CompanyVO companyVo) {
        //当前页下标的计算
        int currentPage = Integer.parseInt(companyVo.getPage());
        int currentCount = Integer.parseInt(companyVo.getLimit());
        int currentIndex = (currentPage-1)*currentCount;

        ResultInfo info = new ResultInfo();
        // 把参数重新封装成对象
        CompanyVO companyVO2 = new CompanyVO();
        companyVO2.setCompanyName(companyVo.getCompanyName());
        companyVO2.setAddress(companyVo.getAddress());
        companyVO2.setHotLineTel(companyVo.getHotLineTel());
        companyVO2.setCurrentIndex(currentIndex);
        companyVO2.setCurrentCount(currentCount);

        //List<Company> companyModels = companyMapper.selectCompanyByLimit(companyVo.getCompanyName(), companyVo.getAddress(), companyVo.getHotLineTel(), currentIndex, currentCount);
        List<Company> companyModels = companyMapper.selectCompanyByLimit(companyVO2);
        int count = companyMapper.findCompanyCount();
        if (companyModels != null && companyModels.size() > 0){
            info.setMsg("查询到信息了！");
            info.setCode(0); //表示查询到信息，并且前端必须规定该值为0才能渲染表格
            info.setCount(count);
            info.setData(companyModels);
            return info;
        }
        info.setCode(1); //表示没有查询到信息
        info.setMsg("没有查询到信息！");

        return info;
    }


    /**
     * 新增公司信息
     * @param company
     * @return
     */
    @Override
    public ResultInfo addCompany(Company company) {
        // 其他参数校验
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        //调用持久层的方法
        int rol = companyMapper.addCompany(company);
        ResultInfo info = new ResultInfo();
        if (rol <= 0){
            info.setMsg("添加失败！请重新添加！");
            return info;
        }
        info.setMsg("添加成功！");
        return info;
    }


    /**
     * 单条或者多条删除操作
     * @param ids
     * @return
     */
    @Override
    public ResultInfo delCompany(String[] ids) {
        ResultInfo info = new ResultInfo();
        for (int i = 0; i < ids.length; i++) {
            int row = companyMapper.delCompany(Integer.parseInt(ids[i]));
        }
        info.setMsg("删除成功！");
        return info;
    }


    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @Override
    public ResultInfo EditCompany(Company company) {
        company.setUpdateTime(new Date());
        //调用持久层的方法
        ResultInfo info = new ResultInfo();
        int rol = companyMapper.editCompany(company);
        if (rol > 0){
            info.setMsg("修改成功！");
            return info;
        }
        info.setMsg("修改失败！");
        return info;
    }
}
