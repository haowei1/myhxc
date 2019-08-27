package com.hdy.myhxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.mapper.MenuMapper;
import com.hdy.myhxc.mapper.RoleMapper;
import com.hdy.myhxc.mapper.ex.RoleExMapper;
import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.MenuExample;
import com.hdy.myhxc.model.ex.RoleMenu;
import com.hdy.myhxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/27
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleExMapper roleExMapper;

    @Override
    public ResultData getList(String roleName, int page, int limit) {
        ResultData resultData = new ResultData();
        PageHelper.startPage(page, limit);
        ArrayList<String> menuUuidList = new ArrayList<>();
        List<RoleMenu> roleList = roleExMapper.getRole(roleName);
        if (roleList != null && roleList.size() > 0) {
            PageInfo<RoleMenu> pageInfo = new PageInfo<>(roleList);
            resultData.setCount((int) pageInfo.getTotal());
            for (RoleMenu roleMenu : roleList) {
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

}
