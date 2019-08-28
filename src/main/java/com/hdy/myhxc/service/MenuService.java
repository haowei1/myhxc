package com.hdy.myhxc.service;

import com.hdy.myhxc.entity.ResultData;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public interface MenuService {

    /**
     * 获取所有菜单信息
     * @param page
     * @param limit
     * @return
     */
    ResultData getMenuList(int page, int limit);

    /**
     * 根据id删除菜单信息（如果是父菜单，则删除其下的所有子菜单）
     * @param uuid
     * @return
     */
    int delMenu(String uuid);

    /**
     * 根据id获取菜单信息
     * @param uuid
     * @return
     */
    ResultData getMenu(String uuid);
}
