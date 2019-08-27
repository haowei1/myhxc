package com.hdy.myhxc.service;

import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.User;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/23
 */
public interface UserService {

    /**
     * 登录 根据姓名和密码查询
     * @param name
     * @param password
     * @return
     */
    User login(String name, String password);

    /**
     * 获取左侧用户菜单
     * @return
     */
    ResultData getUserMenu();

    /**
     * 查询所有用户信息
     * @param userNm
     * @param page
     * @param limit
     * @return
     */
    ResultData getUserList(String userNm, int page, int limit);

    /**
     * 用来编辑学生，可新增或修改
     * @param user
     * @return
     */
    int editUser(User user);

    /**
     * 获取当前用户的权限信息
     * @param uuid
     * @return
     */
    User getUserRole(String uuid);

}
