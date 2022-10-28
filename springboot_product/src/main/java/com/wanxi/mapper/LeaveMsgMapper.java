package com.wanxi.mapper;

import com.wanxi.pojo.LeaveMsg;
import com.wanxi.vo.LeaveMsgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 留言信息的持久层
 * @author: fxf
 * @date: 2022/8/27 17:44
 */
@Mapper
public interface LeaveMsgMapper {

    /**
     * 根据查询条件和分页参数去查询信息
     * @param leaveMsgVO
     * @return
     */
    List<LeaveMsg> selectByConditionAndPage(LeaveMsgVO leaveMsgVO);

    /**
     * 查询共用多少条记录
     * @return
     */
    int findLeaveMsgCount();

    /**
     * 删除留言信息即修改留言的状态
     * @param id
     */
    void updateStatus(Integer id);


    int editWord(LeaveMsg leaveMsg);
}
