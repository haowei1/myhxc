package com.hdy.myhxc.service.impl;

import com.hdy.myhxc.exception.AppException;
import com.hdy.myhxc.mapper.UserMapper;
import com.hdy.myhxc.model.User;
import com.hdy.myhxc.model.UserExample;
import com.hdy.myhxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public int insUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updUser(User user) {
        return userMapper.updateByExampleSelective(user, new UserExample());
    }

    @Override
    public int delById(String uuid) {
        return userMapper.deleteByPrimaryKey(uuid);
    }

    @Override
    public User login(String name, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserCodeEqualTo(name).andUserPsdEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            throw new AppException("用户名和密码不正确，请重新输入！");
        } else {
            return users.get(0);
        }
    }
}
