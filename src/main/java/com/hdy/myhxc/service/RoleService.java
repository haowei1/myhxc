package com.hdy.myhxc.service;

import com.hdy.myhxc.entity.ResultData;

/**
 * @author m760384371
 * @date 2019/8/27
 */
public interface RoleService {

    /**
     * 查询当前用户的权限
     * @param roleName
     * @param page
     * @param limit
     * @return
     */
    ResultData getList(String roleName, int page, int limit);

}
