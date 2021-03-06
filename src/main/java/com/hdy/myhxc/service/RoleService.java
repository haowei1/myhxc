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
     * 查询所有用户的权限信息
     * @param roleName
     * @param page
     * @param limit
     * @return
     */
    ResultData getList(String roleName, int page, int limit);


    /**
     * 根据id删除角色
     * @param uuid
     * @return
     */
    int delRole(String uuid);

    /**
     * 根据id删除多个角色
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
     * 根据id修改角色权限信息
     * @param roleId
     * @param menuIds
     * @return
     */
    int addRole(String roleId, List<String> menuIds);

    /**
     * 根据id编辑角色信息，无id则新增
     * @param uuid
     * @param roleName
     * @return
     */
    int editRole(String uuid, String roleName);
}
