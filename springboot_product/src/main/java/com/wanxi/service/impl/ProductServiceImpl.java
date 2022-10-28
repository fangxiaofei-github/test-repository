package com.wanxi.service.impl;

import com.wanxi.mapper.ProductMapper;
import com.wanxi.pojo.Product;
import com.wanxi.service.ProductService;
import com.wanxi.vo.ProductVO;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 产品信息的业务层
 * @author: fxf
 * @date: 2022/8/27 8:53
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据查询条件和分页参数查询信息
     * @param productVO
     * @return
     */
    @Override
    public ResultInfo selectByConditionAndPage(ProductVO productVO) {
        ResultInfo info = new ResultInfo();
        // 获取起始索引
        Integer pageIndex = Integer.parseInt(productVO.getPage());
        Integer offset = Integer.parseInt(productVO.getLimit());
        Integer index = (pageIndex-1)*offset;
        productVO.setCurrentIndex(index);
        productVO.setCurrentCount(offset);

        //调用业务层的相关方法
        List<Product> productModels = productMapper.selectByConditionAndPage(productVO);
        if (productModels == null || productModels.size() < 1){
            info.setMsg("没有查询到信息！");
            return info;
        }
        info.setMsg("查询到信息！");
        info.setCode(0);
        info.setCount(productMapper.findProductCount());
        info.setData(productModels);
        return info;
    }

    /**
     * 添加产品信息
     * @param product
     * @return
     */
    @Override
    public ResultInfo addProduct(Product product) {
        ResultInfo info = new ResultInfo();
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        // 调用持久层的新增方法
        int row = productMapper.addProduct(product);
        if (row < 1){
            info.setMsg("新增失败！");
            return info;
        }
        info.setMsg("新增成功！");
        return info;
    }

    /**
     * 根据id去该status值
     * @param ids
     * @return
     */
    @Override
    public ResultInfo updateProductStatus(String[] ids) {
        ResultInfo info = new ResultInfo();
        for (int i = 0; i < ids.length; i++) {
            // 调用持久层的删除操作
            productMapper.updateProductStatus(Integer.parseInt(ids[i]));
        }
        info.setMsg("删除成功！");
        return info;
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    @Override
    public ResultInfo editProduct(Product product) {
        ResultInfo info = new ResultInfo();
        product.setUpdateTime(new Date());
        // 调用持久层的修改方法
        int row = productMapper.editProduct(product);
        if (row < 1){
            info.setMsg("修改失败！");
            return info;
        }
        info.setMsg("修改成功！");
        return info;
    }


    /**
     * 根据产品id查询产品信息
     * @param id
     * @return
     */
    @Override
    public ResultInfo findProductById(String id) {
        ResultInfo info = new ResultInfo();
        Product product = productMapper.findProductById(id);
        if (product == null){
            info.setMsg("查询失败！");
            info.setCode(404);
            return info;
        }
        info.setMsg("查询成功！");
        info.setData(product);
        info.setCode(200);
        return info;
    }
}
