package com.hdy.myhxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.entity.TreeNode;
import com.hdy.myhxc.entity.UserMenu;
import com.hdy.myhxc.exception.AppException;
import com.hdy.myhxc.mapper.MenuMapper;
import com.hdy.myhxc.mapper.RoleAuthorityMapper;
import com.hdy.myhxc.mapper.UserMapper;
import com.hdy.myhxc.mapper.UserRoleMapper;
import com.hdy.myhxc.model.*;
import com.hdy.myhxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private HttpServletRequest request;


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


    @Override
    public ResultData getUserMenu() {
        List<TreeNode> tree = new ArrayList<>();
        ResultData resultData = new ResultData();
        // 查询所有菜单信息
        MenuExample menuExample = new MenuExample();
        menuExample.setOrderByClause("SORT");
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        // 获取当前登录的用户信息
        User loginInfo = (User) request.getSession().getAttribute("userInfo");
        List<String> menuUuids = new ArrayList<>();
        if (loginInfo != null) {
            // 获取当前用户权限信息
            UserRoleExample userroleExample = new UserRoleExample();
            userroleExample.createCriteria().andUserUuidEqualTo(loginInfo.getUuid());
            List<UserRole> userroleList = userRoleMapper.selectByExample(userroleExample);
            if (userroleList.size() > 0 && userroleList != null) {
                for (UserRole userrole : userroleList) {
                    // 如果不为空，遍历循环获取相应的内容
                    RoleAuthorityExample roleauthorityExample = new RoleAuthorityExample();
                    roleauthorityExample.createCriteria().andRoleUuidEqualTo(userrole.getRoleUuid());
                    List<RoleAuthority> roleauthorityList = roleAuthorityMapper.selectByExample(roleauthorityExample);
                    if (roleauthorityList.size() > 0 && roleauthorityList != null) {
                        for (RoleAuthority roleauthority : roleauthorityList) {
                            menuUuids.add(roleauthority.getMenuId());
                        }
                    }
                }
            }


            for (Menu menu : menuList) {
                if (menu.getParentId() != null && "".equals(menu.getParentId())) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setUuid(menu.getUuid());
                    treeNode.setId(menu.getMenuId());
                    treeNode.setPid(menu.getParentId());
                    treeNode.setText(menu.getMenuName());
                    treeNode.setIconCls(menu.getMenuIco());
                    treeNode.setUrl(menu.getMenuUrl());
                    tree.add(treeNode);
                } else {
                    if (menuUuids.contains(menu.getUuid())) {
                        TreeNode treeNode = new TreeNode();
                        treeNode.setUuid(menu.getUuid());
                        treeNode.setId(menu.getMenuId());
                        treeNode.setText(menu.getMenuName());
                        treeNode.setIconCls(menu.getMenuIco());
                        treeNode.setPid(menu.getParentId());
                        treeNode.setUrl(menu.getMenuUrl());
                        tree.add(treeNode);
                    }
                }
            }

            List<TreeNode> fatherNode = getFatherNode(tree);
            ArrayList<TreeNode> treeNodes = new ArrayList<>();
            for (TreeNode node : fatherNode) {
                treeNodes.add(node);
            }
            String ksName = "";
            UserMenu userMenu = new UserMenu();
            userMenu.setKsName(ksName);
            userMenu.setMenu(treeNodes);
            userMenu.setUserName(loginInfo.getUserNm());
            resultData.setData(userMenu);
        }
        return resultData;
    }


    public final static List<TreeNode> getFatherNode(List<TreeNode> tree){
        List<TreeNode> jsonTreeNode = new ArrayList<>();
        for (TreeNode fatherTree : tree) {
            if (fatherTree.getPid() == null || "".equals(fatherTree.getPid())){
                fatherTree.setChildren(getChildrenNode(fatherTree.getUuid(), tree));
                fatherTree.setState("open");
                jsonTreeNode.add(fatherTree);
            }
        }
        return jsonTreeNode;
    }

    public final static List<TreeNode> getChildrenNode(String pid,List<TreeNode> tree){
        List<TreeNode> list=new ArrayList<>();
        for (TreeNode jsonTreeNode:tree) {
            if (jsonTreeNode.getPid()==null) {
                continue;
            }
            if (jsonTreeNode.getPid().equals(pid)){
                //递归存放二级菜单的子菜单----单对于固定两级的菜单，这句话可有可无
                jsonTreeNode.setChildren(getChildrenNode(jsonTreeNode.getUuid(), tree));
                /*jsonTreeNode.setState("open");*/
                //newTreeNode.add(jsonTreeNode);\
                list.add(jsonTreeNode);
            }
        }
        return list;
    }


    @Override
    public ResultData getUserList(String userNm, int page, int limit) {
        try {
            ResultData resultData = new ResultData();
            PageHelper.startPage(page, limit);
            UserExample userExample = new UserExample();
            if (userNm != null && !userNm.equals("")) {
                userExample.createCriteria().andUserNmLike("%" + userNm + "%");
            }
            userExample.setOrderByClause("Create_Date DESC");
            List<User> userList = userMapper.selectByExample(userExample);
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            if (userList != null && userList.size() > 0) {
                resultData.setCount((int) pageInfo.getTotal());
                resultData.setData(userList);
                return resultData;
            } else {
                throw new AppException("服务器繁忙，请稍后再试！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new AppException("服务器繁忙，请稍后再试！");
        }
    }
}
