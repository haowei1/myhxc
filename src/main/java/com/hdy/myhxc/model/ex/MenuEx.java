package com.hdy.myhxc.model.ex;

import com.hdy.myhxc.model.Menu;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public class MenuEx extends Menu {

    private List<MenuEx> children;

    public List<MenuEx> getChildren() {
        return children;
    }

    public void setChildren(List<MenuEx> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuEx{}";
    }
}
