package com.hdy.myhxc.service.impl;

import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.mapper.UserDepMapper;
import com.hdy.myhxc.mapper.ex.UserDepExMapper;
import com.hdy.myhxc.model.UserDep;
import com.hdy.myhxc.model.UserDepExample;
import com.hdy.myhxc.model.ex.UserDepEx;
import com.hdy.myhxc.service.UserDepService;
import com.hdy.myhxc.util.DateUtil;
import com.hdy.myhxc.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@Service
public class UserDepServiceImpl implements UserDepService {

    @Autowired
    private UserDepMapper userDepMapper;
    @Autowired
    private UserDepExMapper userDepExMapper;

    @Override
    public ResultData getDepList(int page, int limit) {
        ResultData resultData = new ResultData();
        List<UserDepEx> list = userDepExMapper.getListForLevel1();
        List<UserDep> userDepList = new ArrayList<>();
        getChildren(userDepList, list);
        resultData.setData(userDepList);
        resultData.setCount(userDepList.size());
        return resultData;
    }

    //test递归
    public static void getChildren(List<UserDep> retData, List<UserDepEx> list){
        if (list.size() > 0 && list != null){
            for (UserDepEx userDep : list) {
                retData.add(userDep);
                if (userDep.getChildren()!=null){
                    List<UserDepEx> children = userDep.getChildren();
                    if (children.size() > 0 && null != children){
                        getChildren(retData, children);
                    }
                }else {
                    continue;
                }
            }
        }
    }

    @Override
    public ResultData getDep(String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(userDepMapper.selectByPrimaryKey(uuid));
        return resultData;
    }


    @Override
    public int delDep(String uuid) {
        int i = 0;
        i = userDepMapper.deleteByPrimaryKey(uuid);
        UserDepExample userDepExample = new UserDepExample();
        userDepExample.createCriteria().andDepTopidEqualTo(uuid);
        List<UserDep> userDepList = userDepMapper.selectByExample(userDepExample);
        if (userDepList.size() > 0 && !userDepList.equals("")) {
            for (UserDep userDep : userDepList) {
                i += userDepMapper.deleteByPrimaryKey(userDep.getUuid());
                UserDepExample example = new UserDepExample();
                example.createCriteria().andDepTopidEqualTo(userDep.getUuid());
                i += userDepMapper.deleteByExample(example);
            }
        }
        return i;
    }

    @Override
    public int editDep(UserDep userDep) {
        if (userDep.getUuid() == null || userDep.getUuid().equals("")) {
            userDep.setUuid(UUIDUtil.generateUUID());
            // 新增 为false
            setDepLevel(userDep, false);
            userDep.setUpdateDate(DateUtil.currentTime());
            return userDepMapper.insert(userDep);
        } else {
            // 更新 为true
            setDepLevel(userDep,true);
            userDep.setUpdateDate(DateUtil.currentTime());
            return userDepMapper.updateByPrimaryKeySelective(userDep);
        }
    }

    /**
     * 封装编辑操作
     * @param userDep 实体类
     * @param isUpdFlg 是否是更新 更新为true 新增为false
     */
    protected void setDepLevel(UserDep userDep, Boolean isUpdFlg){
        if (userDep.getDepTopid() == null || userDep.getDepTopid().equals("")){
            //如果没有上级菜单，那么他就是一级菜单
            userDep.setDepLevel(1);
            if (!isUpdFlg) {
                //插入操作  并排序
                UserDepExample userDepExample = new UserDepExample();
                userDepExample.createCriteria().andDepTopidEqualTo("");
                userDepExample.setOrderByClause("Show_Idx DESC");
                List<UserDep> userDepList = userDepMapper.selectByExample(userDepExample);
                if (userDepList.size() > 0 && userDepList != null) {
                    userDep.setShowIdx(userDepList.get(0).getShowIdx() + 1);
                } else {
                    userDep.setShowIdx(1);
                }
            }
        } else {
            UserDep dep = userDepMapper.selectByPrimaryKey(userDep.getDepTopid());
            if (dep != null || !dep.equals("")) {
                userDep.setDepLevel(dep.getDepLevel() + 1);
                userDep.setDepTopname(dep.getDepName());
            } else {
                userDep.setDepLevel(1);
            }
            if (!isUpdFlg) {
                UserDepExample userDepExample = new UserDepExample();
                userDepExample.createCriteria().andDepTopidEqualTo(userDep.getDepTopid());
                userDepExample.setOrderByClause("Show_Idx DESC");
                List<UserDep> userDepList = userDepMapper.selectByExample(userDepExample);
                if (userDepList.size() > 0 && !userDepList.equals("")) {
                    int i = 0;
                    for (UserDep depList : userDepList) {
                        i += 1;
                        depList.setShowIdx(i);
                        depList.setUpdateDate(DateUtil.currentTime());
                        userDepMapper.updateByPrimaryKeySelective(depList);
                    }
                    userDep.setShowIdx(i + 1);
                } else {
                    userDep.setShowIdx(1);
                }
            }
        }
    }
}
