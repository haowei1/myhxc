package com.hdy.myhxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.mapper.MenuMapper;
import com.hdy.myhxc.mapper.RoleAuthorityMapper;
import com.hdy.myhxc.mapper.RoleMapper;
import com.hdy.myhxc.mapper.ex.RoleExMapper;
import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.MenuExample;
import com.hdy.myhxc.model.RoleAuthority;
import com.hdy.myhxc.model.RoleAuthorityExample;
import com.hdy.myhxc.model.ex.RoleEx;
import com.hdy.myhxc.service.RoleService;
import com.hdy.myhxc.util.DateUtil;
import com.hdy.myhxc.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/27
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleExMapper roleExMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public ResultData getList(String roleName, int page, int limit) {
        ResultData resultData = new ResultData();
        PageHelper.startPage(page, limit);
        ArrayList<String> menuUuidList = new ArrayList<>();
        List<RoleEx> roleList = roleExMapper.getRole(roleName);
        if (roleList != null && roleList.size() > 0) {
            PageInfo<RoleEx> pageInfo = new PageInfo<>(roleList);
            resultData.setCount((int) pageInfo.getTotal());
            for (RoleEx roleMenu : roleList) {
                if (roleMenu.getMenuList() != null && roleMenu.getMenuList().size() > 0) {
                    for (Menu menu : roleMenu.getMenuList()) {
                        menuUuidList.add(menu.getUuid());
                    }
                }
                String roleAuthority = "";

                MenuExample menuExample = new MenuExample();
                menuExample.setOrderByClause("Leavel_ID, SORT");
                List<Menu> menuList = menuMapper.selectByExample(menuExample);
                for (Menu menu : menuList) {
                    if (menuUuidList.contains(menu.getUuid())) {
                        if (!"".equals(roleAuthority)) {
                            roleAuthority += ",";
                        }
                        roleAuthority += menu.getMenuName();
                    }
                }
                roleMenu.setRoleAuthority(roleAuthority);
            }
        }
        resultData.setData(roleList);
        return resultData;
    }


    @Override
    public int delRole(String uuid) {
        int i = 0;
        i += roleMapper.deleteByPrimaryKey(uuid);
        RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
        roleAuthorityExample.createCriteria().andRoleUuidEqualTo(uuid);
        i += roleAuthorityMapper.deleteByExample(roleAuthorityExample);
        return i;
}

    @Override
    public int delRoles(String[] uuids) {
        int i = 0;
        for (String uuid : uuids) {
            i += roleMapper.deleteByPrimaryKey(uuid);
            RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
            roleAuthorityExample.createCriteria().andRoleUuidEqualTo(uuid);
            i += roleAuthorityMapper.deleteByExample(roleAuthorityExample);
        }
        return i;
    }

    @Override
    public ResultData getRoleList(String uuid) {
        ResultData resultData = new ResultData();
        RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
        roleAuthorityExample.createCriteria().andRoleUuidEqualTo(uuid);
        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.selectByExample(roleAuthorityExample);
        resultData.setData(roleAuthorityList);
        return resultData;
    }

    @Override
    public int addRole(String roleId, List<String> menuIds) {
        int i = 0;
        RoleAuthorityExample roleAuthorityExample = new RoleAuthorityExample();
        roleAuthorityExample.createCriteria().andRoleUuidEqualTo(roleId);
        roleAuthorityMapper.deleteByExample(roleAuthorityExample);
        removeDuplicate(menuIds);
        for (String menuId : menuIds) {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setUuid(UUIDUtil.generateUUID());
            roleAuthority.setRoleUuid(roleId);
            roleAuthority.setMenuId(menuId);
            roleAuthority.setCreateDate(DateUtil.currentTime());
            i += roleAuthorityMapper.insertSelective(roleAuthority);
        }
        return i;
    }


    /**
     * 方法  TODO 理解方法意思
     * @param list
     * @return
     */
    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    private List<String> removeDuplicate(List<String> list){
        HashSet h=new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
