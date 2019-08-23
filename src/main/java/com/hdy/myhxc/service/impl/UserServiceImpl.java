package com.hdy.myhxc.service.impl;

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
    public List<User> login(String name, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserCodeEqualTo(name).andUserPsdEqualTo(password);
        return userMapper.selectByExample(userExample);
    }
}
