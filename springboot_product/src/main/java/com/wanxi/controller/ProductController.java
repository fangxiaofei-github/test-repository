package com.wanxi.controller;

import com.wanxi.pojo.Product;
import com.wanxi.service.ProductService;
import com.wanxi.vo.ProductVO;
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
 * @description: 产品信息的控制层
 * @author: fxf
 * @date: 2022/8/27 8:46
 */
@Api(tags = "产品信息的控制层模块")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据查询条件和分页参数查询信息
     */
    @ApiOperation("根据查询条件和分页参数查询信息")
    @GetMapping("/productInfo")
    @PreAuthorize("hasAuthority('product:get')")
    public ResultInfo selectByConditionAndPage(ProductVO productVO){
        // 调用业务的相关方法
        ResultInfo info = productService.selectByConditionAndPage(productVO);
        return info;
    }


    /**
     * 添加产品信息
     * @param product
     * @return
     */
    @ApiOperation("添加产品信息")
    @PostMapping("/productInfoAdd")
    @PreAuthorize("hasAuthority('product:add')")
    public ResultInfo addProduct(@RequestBody Product product){
        System.out.println(product);
        // 调用业务层的新增方法
        ResultInfo info = productService.addProduct(product);
        return info;
    }

    /**
     * 删除产品信息（实际上是把产品信息的状态该为0）
     * @return
     */
    @ApiOperation("删除产品信息（实际上是把产品信息的状态该为0）")
    @PreAuthorize("hasAuthority('product:delete')")
    @GetMapping("/productInfoDel")
    public ResultInfo delProduct(String ids){
        // 拆分ids
        String[] idArr = ids.split(",");
        // 调用业务的删除操作
        ResultInfo info =  productService.updateProductStatus(idArr);
        return info;
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    @ApiOperation("修改产品信息")
    @PreAuthorize("hasAuthority('product:update')")
    @PostMapping("/productInfoEdit")
    public ResultInfo editProduct(@RequestBody Product product){
        // 调用业务层的修改方法
        ResultInfo info = productService.editProduct(product);
        return info;
    }

    /**
     * 根据产品id查询产品信息
     * @return
     */
    @ApiOperation("根据产品id查询产品信息")
    @GetMapping("/findProductById")
    public ResultInfo findProductById(String id){
        System.out.println("根据产品id查询产品信息");
        // 调用业务层的修改方法
        ResultInfo info = productService.findProductById(id);
        System.out.println(info.getData());
        return info;
    }
}
