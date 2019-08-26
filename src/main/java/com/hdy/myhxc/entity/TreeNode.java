package com.hdy.myhxc.entity;

import java.util.List;
import java.util.Map;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public class TreeNode {

    private String uuid;
    private String id;
    private String pid;
    private String text;
    private String state;
    private boolean check;
    private String iconCls;
    private String simno;
    private String url;
    private Map<String,Object> otherInfo;
    public List<TreeNode> children;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getSimno() {
        return simno;
    }

    public void setSimno(String simno) {
        this.simno = simno;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Map<String, Object> otherInfo) {
        this.otherInfo = otherInfo;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
