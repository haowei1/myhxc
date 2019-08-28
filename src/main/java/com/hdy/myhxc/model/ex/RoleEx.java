package com.hdy.myhxc.model.ex;

import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.Role;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/27
 */
public class RoleEx extends Role {

    private String userRoleUuid;
    private String roleAuthority;
    private List<Menu> menuList;
    private List<String> menuUuid;

    public String getUserRoleUuid() {
        return userRoleUuid;
    }

    public void setUserRoleUuid(String userRoleUuid) {
        this.userRoleUuid = userRoleUuid;
    }

    public String getRoleAuthority() {
        return roleAuthority;
    }

    public void setRoleAuthority(String roleAuthority) {
        this.roleAuthority = roleAuthority;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<String> getMenuUuid() {
        return menuUuid;
    }

    public void setMenuUuid(List<String> menuUuid) {
        this.menuUuid = menuUuid;
    }
}
