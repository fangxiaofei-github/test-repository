package com.wanxi.controller;

import com.wanxi.pojo.Company;
import com.wanxi.service.CompanyService;
import com.wanxi.vo.CompanyVO;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 公司信息相关的控制层
 * @author: fxf
 * @date: 2022/8/25 21:41
 */
@Api(tags = "公司信息模块")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /**
     * 根据查询参数和分页条件查询公司信息
     *    (Layui数据表格的接口数据请求方式为Get。就不要用@RequestBody在对象参数前面了)
     * @return
     */
    @ApiOperation("根据查询参数和分页条件查询公司信息")
    @GetMapping("/companyInfo")
    @PreAuthorize("hasAuthority('company:get')")
    public ResultInfo selectCompanyByLimit(CompanyVO companyVO){
        //调用业务层的相关方法
        ResultInfo resultInfo = companyService.selectCompanyByLimit(companyVO);
        return resultInfo;
    }


    /**
     * 新增公司信息
     * @param company
     * @return
     */
    @ApiOperation("新增公司信息")
    @PostMapping("/companyInfoAdd")
    @PreAuthorize("hasAuthority('company:add')")
    public ResultInfo addCompany(@RequestBody Company company){
        System.out.println(company);
        //调用业务层的公司新增方法
        ResultInfo info = companyService.addCompany(company);
        return info;
    }


    /**
     * 单条或者多条删除操作
     * @return
     */
    @ApiOperation("单条或者多条删除操作")
    @GetMapping("/companyInfoDel")
    @PreAuthorize("hasAuthority('company:delete')")
    public ResultInfo delCompany(String ids){
        // 拆分参数
        String[] idArr = ids.split(",");
        // 调用业务层的批量删除方法
        ResultInfo info = companyService.delCompany(idArr);
        return info;
    }


    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @ApiOperation("修改公司信息")
    @PostMapping("/companyInfoEdit")
    @PreAuthorize("hasAuthority('company:update')")
    public ResultInfo EditCompany(@RequestBody Company company){
        //调用业务层的公司新增方法
        ResultInfo info = companyService.EditCompany(company);
        return info;
    }



}
