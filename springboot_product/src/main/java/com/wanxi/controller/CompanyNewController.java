package com.wanxi.controller;

import com.wanxi.pojo.CompanyNew;
import com.wanxi.service.CompanyNewService;
import com.wanxi.vo.CompanyNewVO;
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
 * @description: 公司新闻信息的控制层
 * @author: fxf
 * @date: 2022/8/26 11:29
 */
@Api(tags = "司新闻信息模块")
@RestController
public class CompanyNewController {

    @Autowired
    private CompanyNewService companyNewService;

    /**
     * 根据查询参数和分页条件查询信息
     * @param companyNewVO
     * @return
     */
    @ApiOperation("根据查询参数和分页条件查询信息")
    @GetMapping("/companyNew")
    @PreAuthorize("hasAuthority('companyNew:get')")
    public ResultInfo selectByConditionAndPage(CompanyNewVO companyNewVO){
        //调用业务的方法：通过条件查询新闻信息
        ResultInfo info = companyNewService.selectByConditionAndPage(companyNewVO);
        //System.out.println("查询：接收后台的数据--"+info);
        return info;
    }

    /**
     * 添加公司新闻信息
     * @return
     */
    @ApiOperation("添加公司新闻信息")
    @PostMapping("/companyNewInfoAdd")
    @PreAuthorize("hasAuthority('companyNew:add')")
    public ResultInfo addCompanyNew(@RequestBody CompanyNew companyNew){
        // 调用业务相关的新增方法
        ResultInfo info = companyNewService.addCompanyNew(companyNew);
        return info;
    }

    /**
     * 新闻信息删除操作（不是真正删除而是逻辑删除）
     * @param ids
     * @return
     */
    @ApiOperation("新闻信息删除操作（不是真正删除而是逻辑删除）")
    @GetMapping("/companyNewInfoDel")
    @PreAuthorize("hasAuthority('companyNew:delete')")
    public ResultInfo updateCompanyNewStatus(String ids){
        // 拆分字符串成数组
        String[] idArr = ids.split(",");
        // 调用业务层的相关的方法
        ResultInfo info = companyNewService.updateCompanyNewStatus(idArr);
        return info;
    }

    /**
     * 修改新闻信息
     * @param companyNew
     * @return
     */
    @ApiOperation("修改新闻信息")
    @PostMapping("/companyNewInfoEdit")
    @PreAuthorize("hasAuthority('companyNew:update')")
    public ResultInfo editCompanyNew(@RequestBody CompanyNew companyNew){
        //System.out.println("修改：接收前台数据--"+companyNew);
        // 调用业务相关的修改方法
        ResultInfo info = companyNewService.editCompanyNew(companyNew);
        return info;
    }


}
