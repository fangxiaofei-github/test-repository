package com.wanxi.mapper;

import com.wanxi.pojo.EsNav;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @description: ES相关的持久层
 * @author: fxf
 * @date: 2022/10/8 14:39
 */

public interface EsNavRepository extends ElasticsearchRepository<EsNav,Integer> {

}
