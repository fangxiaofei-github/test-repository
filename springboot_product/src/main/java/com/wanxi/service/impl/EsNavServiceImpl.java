package com.wanxi.service.impl;

import com.wanxi.mapper.EsNavRepository;
import com.wanxi.mapper.NavMapper;
import com.wanxi.pojo.EsNav;
import com.wanxi.service.EsNavService;
import com.wanxi.vo.ResultInfo;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: es相关的业务层
 * @author: fxf
 * @date: 2022/10/8 14:43
 */
@Service
public class EsNavServiceImpl implements EsNavService {

    @Autowired
    private NavMapper navMapper;

    @Autowired
    private EsNavRepository esNavRepository;

    //在新版的依赖中，ElasticsearchRestTemplate代替了原来的ElasticsearchTemplate
    @Autowired
    private ElasticsearchRestTemplate esTemplate;


    /**
     * 从数据库中导入所有商品到ES
     */
    @Override
    public void findAllToES(){
        // 先查询所有商品信息
        List<EsNav> navList = navMapper.findAll();
        // 然后把查询出来的数据存储到ES中去
        esNavRepository.saveAll(navList);
    }


    /**
     * 根据查询条件和分页参数查询信息
     */
    @Override
    public ResultInfo selectByConditionAndPage(EsNav esNav) {
        ResultInfo info = new ResultInfo();
        //从数据库中导入所有商品到ES
        findAllToES();
        // 计算出分页查询的起始下标
        Integer pageIndex = Integer.parseInt(esNav.getPage());
        Integer offset = Integer.parseInt(esNav.getLimit());
        Integer index = (pageIndex - 1)*offset;
        esNav.setCurrentIndex(index);
        esNav.setCurrentCount(offset);

        /**
         * 开始es查询 [高级查询：分页、排序、高亮]
         */
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //查询条件--匹配查询，如果大于10条，默认只显示10条
                .withQuery(QueryBuilders.multiMatchQuery(esNav.getNavName(),"navName"))
                //分页
                .withPageable(PageRequest.of(esNav.getCurrentIndex(), esNav.getCurrentCount()))
                //分数排序,默认是降序
                //.withSort(SortBuilders.scoreSort().order(SortOrder.ASC))
                //字段排序
                //.withSort(SortBuilders.fieldSort("navName").order(SortOrder.ASC))
                //高亮，默认是<em>标签斜体。
                //.withHighlightFields(new HighlightBuilder.Field("navName"))
                .withHighlightFields(new HighlightBuilder.Field("navName")
                        .preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        SearchHits<EsNav> hits = esTemplate.search(query, EsNav.class);
        //循环获取里面的值获取到内容放入集合中
        List<EsNav> esNavList = new ArrayList<>();
        for (SearchHit<EsNav> hit : hits) {
            String id = hit.getId();
            float score = hit.getScore();
            EsNav content = hit.getContent();
            //=====================================================
            esNavList.add(content);
            String navName = hit.getHighlightFields().get("navName").get(0);
            //把加高亮的字段存储到实体类中
            content.setNavName(navName);
            //=====================================================
            System.out.println("id = " + id);
            System.out.println("score = " + score);
            System.out.println("content = " + content);
            System.out.println("navName = " + navName);
            System.out.println("=====================");
        }
        info.setCode(0);
        info.setCount(esNavList.size());
        info.setData(esNavList);
        return info;
    }




}
