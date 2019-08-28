package com.hdy.myhxc.service;

import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.ex.RoleEx;

import java.util.List;

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


    /**
     * 根据id删除权限
     * @param uuid
     * @return
     */
    int delRole(String uuid);

    /**
     * 根据id删除多个权限
     * @param uuids
     * @return
     */
    int delRoles(String[] uuids);

    /**
     * 根据id获取角色信息
     * @param uuid
     * @return
     */
    ResultData getRoleList(String uuid);

    /**
     * 根据id修改权限信息
     * @param roleId
     * @param menuIds
     * @return
     */
    int addRole(String roleId, List<String> menuIds);


}
