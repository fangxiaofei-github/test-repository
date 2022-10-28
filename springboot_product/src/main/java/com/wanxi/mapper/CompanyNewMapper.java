package com.wanxi.mapper;

import com.wanxi.pojo.CompanyNew;
import com.wanxi.vo.CompanyNewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 公司新闻相关的操作
 * @author: fxf
 * @date: 2022/8/26 11:44
 */
@Mapper
public interface CompanyNewMapper {

    /**
     * 根据参数和分页条件查询信息
     * @param companyNewVO
     * @return
     */
    List<CompanyNew> selectByConditionAndPage(CompanyNewVO companyNewVO);


    /**
     * 查询表中共有几条数据
     * @return
     */
    int findCompanyNewCount();

    /**
     * 添加公司新闻信息
     * @param companyNew
     * @return
     */
    int addCompanyNew(CompanyNew companyNew);

    /**
     * 改变对应id记录的状态
     * @param id
     */
    void updateCompanyNewStatus(int id);

    /**
     * 修改新闻信息
     * @param companyNew
     * @return
     */
    int editCompanyNew(CompanyNew companyNew);
}
