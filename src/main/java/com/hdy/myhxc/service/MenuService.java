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


}
