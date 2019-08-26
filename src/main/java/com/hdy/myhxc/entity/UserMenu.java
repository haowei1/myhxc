package com.hdy.myhxc.entity;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public class UserMenu {

    private String userId;
    private String userName;
    private String ksUuid;
    private String ksName;
    private String userPhone;
    private String roleFlg;
    private List<TreeNode> menu;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKsUuid() {
        return ksUuid;
    }

    public void setKsUuid(String ksUuid) {
        this.ksUuid = ksUuid;
    }

    public String getKsName() {
        return ksName;
    }

    public void setKsName(String ksName) {
        this.ksName = ksName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRoleFlg() {
        return roleFlg;
    }

    public void setRoleFlg(String roleFlg) {
        this.roleFlg = roleFlg;
    }

    public List<TreeNode> getMenu() {
        return menu;
    }

    public void setMenu(List<TreeNode> menu) {
        this.menu = menu;
    }
}
