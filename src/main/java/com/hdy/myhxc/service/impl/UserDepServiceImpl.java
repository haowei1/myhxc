package com.hdy.myhxc.service.impl;

import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.mapper.UserDepMapper;
import com.hdy.myhxc.mapper.ex.UserDepExMapper;
import com.hdy.myhxc.model.UserDep;
import com.hdy.myhxc.model.ex.UserDepEx;
import com.hdy.myhxc.service.UserDepService;
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
        List<UserDepEx> list1 = userDepExMapper.getListForLevel1();
        List<UserDep> userdeps = new ArrayList<>();
        getChildren(userdeps, list1);
        resultData.setData(userdeps);
        resultData.setCount(userdeps.size());
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
}
