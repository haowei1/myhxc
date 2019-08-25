package com.hdy.myhxc.service;

import com.hdy.myhxc.model.User;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/23
 */
public interface UserService {

    /**
     * 查询全部
     * @return
     */
    List<User> selAll();

    /**
     * 新增
     * @param user
     * @return
     */
    int insUser(User user);

    /**
     * 更新
     * @param user
     * @return
     */
    int updUser(User user);

    /**
     * 删除
     * @param uuid
     * @return
     */
    int delById(String uuid);

    /**
     * 登录 根据姓名和密码查询
     * @param name
     * @param password
     * @return
     */
    User login(String name, String password);

}
