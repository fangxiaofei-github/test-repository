package com.wanxi.controller;

import cn.hutool.core.text.csv.CsvUtil;
import com.wanxi.pojo.LeaveMsg;
import com.wanxi.service.LeaveMsgService;
import com.wanxi.vo.LeaveMsgVO;
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
 * @description: 留言信息的控制层
 * @author: fxf
 * @date: 2022/8/27 17:15
 */
@Api(tags = "留言信息的控制层模块")
@RestController
public class LeaveMsgController {

    @Autowired
    private LeaveMsgService leaveMsgService;

    /**
     * 根据查询条件和分页参数去查询信息
     * @param leaveMsgVO
     * @return
     */
    @ApiOperation("根据查询参数和分页条件查询信息")
    @GetMapping("/wordInfo")
    @PreAuthorize("hasAuthority('word:get')")
    public ResultInfo selectByConditionAndPage(LeaveMsgVO leaveMsgVO){
        // 调用业务层的相关查询方法
        ResultInfo info = leaveMsgService.selectByConditionAndPage(leaveMsgVO);
        return info;
    }

    /**
     * 删除留言信息即修改留言的状态
     * @return
     */
    @ApiOperation("删除留言信息即修改留言的状态")
    @GetMapping("/wordInfoDel")
    @PreAuthorize("hasAuthority('word:delete')")
    public ResultInfo delLeaveMsg(String ids){
        // 拆分参数
        String[] idArr = ids.split(",");
        // 调用业务层的删除方法
        ResultInfo info = leaveMsgService.updateStatus(idArr);
        return info;
    }

    /**
     * 修改留言信息
     * @param leaveMsg
     * @return
     */
    @ApiOperation("修改留言信息")
    @PostMapping("/wordInfoEdit")
    @PreAuthorize("hasAuthority('word:update')")
    public ResultInfo editLeaveMsg(@RequestBody LeaveMsg leaveMsg){
        // 调用业务层的修改方法
        ResultInfo info = leaveMsgService.editWord(leaveMsg);
        return info;
    }


    /**
     * 根据留言信息的ID去查询处理用户的信息
     */
    @ApiOperation("根据留言信息的ID去查询处理用户的信息")
    @GetMapping("/selectUserById")
    @PreAuthorize("hasAuthority('word:get')")
    public ResultInfo selectUserById(LeaveMsgVO leaveMsgVO){
        // 调用业务层的相关查询方法
        ResultInfo info = leaveMsgService.selectUserById(leaveMsgVO.getUserId());
        System.out.println(info);
        return info;
    }


}
