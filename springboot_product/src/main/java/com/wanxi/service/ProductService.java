package com.wanxi.service;

import com.wanxi.pojo.Product;
import com.wanxi.vo.ProductVO;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 产品信息的业务层
 * @author: fxf
 * @date: 2022/8/27 8:52
 */
public interface ProductService {

    /**
     * 根据查询条件和分页参数查询信息
     * @param productVO
     * @return
     */
    ResultInfo selectByConditionAndPage(ProductVO productVO);

    /**
     * 添加产品信息
     * @param product
     * @return
     */
    ResultInfo addProduct(Product product);

    /**
     * 根据id改状态值
     * @param ids
     * @return
     */
    ResultInfo updateProductStatus(String[] ids);

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    ResultInfo editProduct(Product product);

    /**
     * 根据产品id查询产品信息
     * @param id
     * @return
     */
    ResultInfo findProductById(String id);
}
