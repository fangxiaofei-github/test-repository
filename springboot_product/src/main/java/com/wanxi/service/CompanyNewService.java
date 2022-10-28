package com.wanxi.service;

import com.wanxi.pojo.CompanyNew;
import com.wanxi.vo.CompanyNewVO;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 公司新闻的业务层
 * @author: fxf
 * @date: 2022/8/26 11:37
 */
public interface CompanyNewService {

    /**
     * 根据参数和分页条件查询信息
     * @param companyNewVO
     * @return
     */
    ResultInfo selectByConditionAndPage(CompanyNewVO companyNewVO);

    /**
     * 添加公司新闻信息
     * @param companyNew
     * @return
     */
    ResultInfo addCompanyNew(CompanyNew companyNew);

    /**
     * 新闻信息删除操作（不是真正删除而是逻辑删除）
     * @param idArr
     * @return
     */
    ResultInfo updateCompanyNewStatus(String[] idArr);

    /**
     * 修改新闻信息
     * @param companyNew
     * @return
     */
    ResultInfo editCompanyNew(CompanyNew companyNew);
}
