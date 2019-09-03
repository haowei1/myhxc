package com.hdy.myhxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.entity.TreeNode;
import com.hdy.myhxc.entity.UserMenu;
import com.hdy.myhxc.exception.AppException;
import com.hdy.myhxc.mapper.*;
import com.hdy.myhxc.model.*;
import com.hdy.myhxc.service.UserService;
import com.hdy.myhxc.util.DateUtil;
import com.hdy.myhxc.util.UUIDUtil;
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
    private UserDepMapper userDepMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public User login(String name, String password) {
        UserExample userExample = new UserExample();
        // 只有用户名和密码同时符合时才可以登录
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
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserUuidEqualTo(loginInfo.getUuid());
                List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
            if (userRoleList.size() > 0 && userRoleList != null) {
                // 如果不为空，遍历循环获取用户对应的菜单信息
                for (UserRole userRole : userRoleList) {
                    RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
                    roleAuthorityExample.createCriteria().andRoleUuidEqualTo(userRole.getRoleUuid());
                    List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.selectByExample(roleAuthorityExample);
                    if (roleAuthorityList.size() > 0 && roleAuthorityList != null) {
                        for (RoleAuthority roleAuthority : roleAuthorityList) {
                            menuUuids.add(roleAuthority.getMenuId());
                        }
                    }
                }
            }
            // 遍历循环放入对应的集合
            for (Menu menu : menuList) {
                // 权限中包含的子菜单
                if (menuUuids.contains(menu.getUuid())) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setUuid(menu.getUuid());
                    treeNode.setId(menu.getMenuId());
                    treeNode.setPid(menu.getParentId());
                    treeNode.setText(menu.getMenuName());
                    treeNode.setIconCls(menu.getMenuIco());
                    treeNode.setUrl(menu.getMenuUrl());
                    tree.add(treeNode);
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

    /**
     *
     * @param tree
     * @return
     */
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

    /**
     *
     * @param pid
     * @param tree
     * @return
     */
    public final static List<TreeNode> getChildrenNode(String pid, List<TreeNode> tree){
        List<TreeNode> list=new ArrayList<>();
        for (TreeNode jsonTreeNode : tree) {
            if (jsonTreeNode.getPid() == null) {
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
        ResultData resultData = new ResultData();
        PageHelper.startPage(page, limit);
        UserExample userExample = new UserExample();
        // name不为空的话进行模糊查询
        if (userNm != null && !userNm.equals("")) {
            userExample.createCriteria().andUserNmLike("%" + userNm + "%");
        }
        // 根据Create_Date 降序
        userExample.setOrderByClause("Create_Date DESC");
        List<User> userList = userMapper.selectByExample(userExample);
        // 分页
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        if (userList != null && userList.size() > 0) {
            resultData.setCount((int) pageInfo.getTotal());
            resultData.setData(userList);
            return resultData;
        } else {
            return resultData;
        }
    }

    @Override
    public int editUser(User user) {
        User loginInfo = (User) request.getSession().getAttribute("userInfo");
        // 如果有部门 设置部门名称
        if (user.getUserDep() != null && !"".equals(user.getUserDep())) {
            UserDep userDep = userDepMapper.selectByPrimaryKey(user.getUserDep());
            if (userDep != null && !"".equals(userDep)){
                user.setUserDepnm(userDep.getDepName());
            } else {
                user.setUserDepnm("");
            }
        }
        // 如果没有id 则为新增
        if (user.getUuid() == null || "".equals(user.getUuid())) {
            // 新增
            UserExample userExample = new UserExample();
            if (user.getUserCode() != null && !"".equals(user.getUserCode().trim())) {
                userExample.createCriteria().andUserCodeEqualTo(user.getUserCode());
            }
            // 如果用户名存在 抛出异常
            List<User> userList = userMapper.selectByExample(userExample);
            if (userList != null && userList.size() > 0) {
                throw new AppException("用户名已存在，请重新输入！");
            }
            String userUuid = UUIDUtil.generateUUID();
            // 将对应的权限加入用户权限表中
            if (user.getUserRole() != null && !"".equals(user.getUserRole())) {
                String[] userRoles = user.getUserRole().split(",");
                UserRoleExample userRoleExample = new UserRoleExample();
                userRoleExample.createCriteria().andUserUuidEqualTo(userUuid);
                userRoleMapper.deleteByExample(userRoleExample);
                for (String userRole : userRoles) {
                    UserRole role = new UserRole();
                    role.setUuid(UUIDUtil.generateUUID());
                    role.setUserUuid(userUuid);
                    role.setRoleUuid(userRole);
                    role.setCreateUser(loginInfo.getUuid());
                    role.setCreateData(DateUtil.currentTime());
                    userRoleMapper.insertSelective(role);
                }
            }
            user.setUserRole("");
            user.setUuid(userUuid);
            user.setCreateUser(loginInfo.getUuid());
            user.setCreateDate(DateUtil.currentTime());
            return userMapper.insertSelective(user);
        } else {
            // 修改
            UserExample userExample = new UserExample();
            if (user.getUserCode() != null && !"".equals(user.getUserCode())){
                userExample.or().andUserCodeEqualTo(user.getUserCode()).andUuidNotEqualTo(user.getUuid());
            }
            List<User> list = userMapper.selectByExample(userExample);
            if (list.size() > 0 && list != null){
                throw new AppException("用户名不能重复");
            }
            // 修改对应的权限表
            if (user.getUserRole() != null && !"".equals(user.getUserRole())){
                String[] userRoles = user.getUserRole().split(",");
                UserRoleExample userRoleExample = new UserRoleExample();
                userRoleExample.createCriteria().andUserUuidEqualTo(user.getUuid());
                userRoleMapper.deleteByExample(userRoleExample);
                for (String userRole : userRoles) {
                    UserRole role = new UserRole();
                    role.setUuid(UUIDUtil.generateUUID());
                    role.setUserUuid(user.getUuid());
                    role.setRoleUuid(userRole);
                    role.setUpdateUser(loginInfo.getUserNm());
                    role.setUpdateDate(DateUtil.currentTime());
                    userRoleMapper.insertSelective(role);
                }
            }
            user.setUserRole("");
            user.setUpdateDate(DateUtil.currentTime());
            user.setUpdateUser(loginInfo.getUuid());
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public User getUserRole(String uuid) {
        User user = userMapper.selectByPrimaryKey(uuid);
        if (user != null && !user.equals("")) {
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserUuidEqualTo(user.getUuid());
            List<UserRole> roleList = userRoleMapper.selectByExample(userRoleExample);
            String userRole = "";
            for (UserRole role : roleList) {
                if (!userRole.equals("")) {
                    userRole = "," + role.getRoleUuid();
                } else {
                    userRole = role.getRoleUuid();
                }
            }
            user.setUserRole(userRole);
        }
        return user;
    }

    @Override
    public int delUser(String uuid) {
        return delUsersByUuid(uuid);
    }

    @Override
    public int delUsers(String[] uuids) {
        int i = 0;
        for (String uuid : uuids) {
            i = delUsersByUuid(uuid);
        }
        return i;
    }

    /**
     * 删除User的封装
     * @param uuid
     * @return
     */
    public int delUsersByUuid(String uuid) {
        int i = 0;
        i += userMapper.deleteByPrimaryKey(uuid);
        // 删除用户对应的角色信息
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserUuidEqualTo(uuid);
        i += userRoleMapper.deleteByExample(userRoleExample);
        return i;
    }

    @Override
    public int initPwd(String uuid) {
        User loginInfo = (User) request.getSession().getAttribute("userInfo");
        User user = new User();
        user.setUserPsd("1");
        user.setUuid(uuid);
        user.setUpdateDate(DateUtil.currentTime());
        user.setUpdatePwdt(DateUtil.currentTime());
        user.setUpdateUser(loginInfo.getUserNm());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
