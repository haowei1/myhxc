package com.hdy.myhxc.service;

import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.UserDep;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public interface UserDepService {


    /**
     * 获取depList
     * @param page
     * @param limit
     * @return
     */
    ResultData getDepList(int page, int limit);

    /**
     * 编辑部门，可用与新增或修改
     * @param userDep
     * @return
     */
    int editDep(UserDep userDep);

    /**
     * 根据id获取当前部门信息
     * @param uuid
     * @return
     */
    ResultData getDep(String uuid);

    /**
     * 根据id删除部门信息
     * @param uuid
     * @return
     */
    int delDep(String uuid);
}
