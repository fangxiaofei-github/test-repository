package com.wanxi.mapper;

import com.wanxi.pojo.Product;
import com.wanxi.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 产品信息的持久层
 * @author: fxf
 * @date: 2022/8/27 8:56
 */
@Mapper
public interface ProductMapper {


    /**
     * 根据查询条件和分页参数查询信息
     * @param productVO
     * @return
     */
    List<Product> selectByConditionAndPage(ProductVO productVO);

    /**
     * 查询一共有多少条记录
     * @return
     */
    Integer findProductCount();

    /**
     * 添加产品信息
     * @param product
     * @return
     */
    int addProduct(Product product);

    /**
     * 根据id去修改status值
     * @param id
     */
    void updateProductStatus(int id);

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    int editProduct(Product product);

    /**
     * 根据产品id查询产品信息
     * @param id
     * @return
     */
    Product findProductById(String id);


}
