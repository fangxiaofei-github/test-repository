package com.wanxi.service;

import com.wanxi.pojo.Company;
import com.wanxi.vo.CompanyVO;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 公司信息相关的业务层
 * @author: fxf
 * @date: 2022/8/25 23:15
 */
public interface CompanyService {


    /**
     * 根据查询参数和分页条件查询公司信息
     * @param companyVo
     * @return
     */
    ResultInfo selectCompanyByLimit(CompanyVO companyVo);

    /**
     * 新增公司信息
     * @param company
     * @return
     */
    ResultInfo addCompany(Company company);

    /**
     * 单条或者多条删除操作
     * @param ids
     * @return
     */
    ResultInfo delCompany(String[] ids);


    /**
     * 修改公司信息
     * @param company
     * @return
     */
    ResultInfo EditCompany(Company company);
}
