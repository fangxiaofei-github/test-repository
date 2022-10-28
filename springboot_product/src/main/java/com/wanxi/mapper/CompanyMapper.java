package com.wanxi.mapper;

import com.wanxi.pojo.Company;
import com.wanxi.vo.CompanyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 公司信息相关的持久层
 * @author: fxf
 * @date: 2022/8/25 23:20
 */
@Mapper
public interface CompanyMapper {

    /**
     * 根据查询参数和分页条件查询公司信息
     */
    //List<Company> selectCompanyByLimit(String companyName, String address, String hotLineTel, Integer currentIndex, Integer currentCount);
    List<Company> selectCompanyByLimit(CompanyVO companyVo);

    /**
     * 查询表中一共有多少条数据
     * @return
     */
    int findCompanyCount();

    /**
     * 新增公司信息
     * @param company
     * @return
     */
    int addCompany(Company company);

    /**
     * 单条或者多条删除操作
     * @param
     * @return
     */
    int delCompany(int id);

    /**
     * 修改公司信息
     * @param company
     * @return
     */
    int editCompany(Company company);
}
