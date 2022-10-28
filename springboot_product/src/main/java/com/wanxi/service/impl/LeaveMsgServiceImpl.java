package com.wanxi.service.impl;

import com.wanxi.mapper.LeaveMsgMapper;
import com.wanxi.mapper.UserMapper;
import com.wanxi.pojo.LeaveMsg;
import com.wanxi.pojo.User;
import com.wanxi.service.LeaveMsgService;
import com.wanxi.vo.LeaveMsgVO;
import com.wanxi.vo.ResultInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: fxf
 * @date: 2022/8/27 17:17
 */
@Service
public class LeaveMsgServiceImpl implements LeaveMsgService {

    @Autowired
    private LeaveMsgMapper leaveMsgMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据查询条件和分页参数去查询信息
     * @param leaveMsgVO
     * @return
     */
    @Override
    public ResultInfo selectByConditionAndPage(LeaveMsgVO leaveMsgVO) {
        ResultInfo info = new ResultInfo();
        // 获取起始下标
        Integer pageIndex = Integer.parseInt(leaveMsgVO.getPage());
        Integer offset = Integer.parseInt(leaveMsgVO.getLimit());
        Integer index = (pageIndex-1)*offset;

        leaveMsgVO.setCurrentIndex(index);
        leaveMsgVO.setCurrentCount(offset);

        // 调用持久层的相关方法
        List<LeaveMsg> formModels = leaveMsgMapper.selectByConditionAndPage(leaveMsgVO);

        if (formModels == null || formModels.size() < 1){
            info.setMsg("没有查询到信息！");
            return info;
        }
        int count = leaveMsgMapper.findLeaveMsgCount();
        info.setMsg("查询到信息了！");
        info.setCode(0);
        info.setCount(count);
        info.setData(formModels);
        return info;
    }

    /**
     * 删除留言信息即修改留言的状态
     * @param idArr
     * @return
     */
    @Override
    public ResultInfo updateStatus(String[] idArr) {
        ResultInfo info = new ResultInfo();
        for (int i = 0; i < idArr.length; i++) {
            leaveMsgMapper.updateStatus(Integer.parseInt(idArr[i]));
        }
        info.setMsg("删除成功！");
        return info;
    }


    /**
     * 修改留言信息
     * @param leaveMsg
     * @return
     */
    @Override
    public ResultInfo editWord(LeaveMsg leaveMsg) {
        ResultInfo info = new ResultInfo();
        leaveMsg.setUpdateTime(new Date());
        // 调用持久层的修改操作
        int row = leaveMsgMapper.editWord(leaveMsg);
        if (row < 1){
            info.setMsg("修改失败！");
            return info;
        }
        info.setMsg("修改成功！");
        return info;
    }


    /**
     * 根据留言信息的ID去查询处理用户的信息
     * @param userId
     * @return
     */
    @Override
    public ResultInfo selectUserById(Integer userId) {
        ResultInfo info = new ResultInfo();
        //非法参数判断
        if (userId == null || "".equals(userId)){
            info.setCode(10);
            info.setMsg("该留言没有对应的用户ID");
            return info;
        }
        List<User> userList = userMapper.selectUserById(userId);

        if (userList == null || userList.size()<0 ){
            info.setCode(10);
            info.setMsg("该用户ID没有对应的用户信息");
            return info;
        }
        info.setData(userList);
        info.setCode(0);
        info.setCount(userList.size());
        info.setMsg("查询到了的用户信息");
        return info;
    }
}
