package com.hdy.myhxc.model.ex;

import com.hdy.myhxc.model.User;
import com.hdy.myhxc.model.UserDep;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public class UserDepEx extends UserDep {

    private List<UserDepEx> children;
    private List<User> userList;

    public UserDepEx() {
    }

    public UserDepEx(List<UserDepEx> children, List<User> userList) {
        this.children = children;
        this.userList = userList;
    }

    public List<UserDepEx> getChildren() {
        return children;
    }

    public void setChildren(List<UserDepEx> children) {
        this.children = children;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "UserDepEx{" +
                "children=" + children +
                ", userList=" + userList +
                '}';
    }
}
