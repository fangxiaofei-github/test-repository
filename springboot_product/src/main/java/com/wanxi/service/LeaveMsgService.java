package com.wanxi.service;

import com.wanxi.pojo.LeaveMsg;
import com.wanxi.vo.LeaveMsgVO;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 留言信息的业务层
 * @author: fxf
 * @date: 2022/8/27 17:16
 */
public interface LeaveMsgService {


    /**
     * 根据查询条件和分页参数去查询信息
     * @param leaveMsgVO
     * @return
     */
    ResultInfo selectByConditionAndPage(LeaveMsgVO leaveMsgVO);


    /**
     * 删除留言信息即修改留言的状态
     * @param idArr
     * @return
     */
    ResultInfo updateStatus(String[] idArr);

    /**
     * 修改留言信息
     * @param leaveMsg
     * @return
     */
    ResultInfo editWord(LeaveMsg leaveMsg);

    /**
     * 根据留言信息的ID去查询处理用户的信息
     * @param userId
     * @return
     */
    ResultInfo selectUserById(Integer userId);
}
