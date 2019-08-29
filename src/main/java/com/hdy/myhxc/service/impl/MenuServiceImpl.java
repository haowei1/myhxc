package com.hdy.myhxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.mapper.MenuMapper;
import com.hdy.myhxc.mapper.ex.MenuExMapper;
import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.MenuExample;
import com.hdy.myhxc.model.User;
import com.hdy.myhxc.model.ex.MenuEx;
import com.hdy.myhxc.service.MenuService;
import com.hdy.myhxc.util.DateUtil;
import com.hdy.myhxc.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuExMapper menuExMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public ResultData getMenuList(int page, int limit) {
        ResultData resultData = new ResultData();
        PageHelper.startPage(page, limit);

        List<MenuEx> menuExList1 = menuExMapper.getListForLevel1();
        List<MenuEx> DataList = new ArrayList<>();
        for (MenuEx temp1 : menuExList1) {
            MenuEx menuEx1 = new MenuEx();
            BeanUtils.copyProperties(temp1, menuEx1);
            DataList.add(menuEx1);
            List<MenuEx> menuExList2 = temp1.getChildren();
            if (menuExList2.size() > 0 && menuExList2 != null) {
                for (MenuEx temp2 : menuExList2) {
                    MenuEx menuEx2=new MenuEx();
                    BeanUtils.copyProperties(temp2, menuEx2);
                    DataList.add(menuEx2);
                }
            }
        }
        resultData.setData(DataList);
        resultData.setCount(DataList.size());
        return resultData;
    }

    @Override
    public int delMenu(String uuid) {
        int i = 0;
        i += menuMapper.deleteByPrimaryKey(uuid);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(uuid);
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        if (menuList.size() > 0 && menuList != null) {
            for (Menu menu : menuList) {
                MenuExample menuExample1 = new MenuExample();
                menuExample1.createCriteria().andParentIdEqualTo(menu.getParentId());
                i += menuMapper.deleteByExample(menuExample1);
            }
        }
        return i;
    }

    @Override
    public ResultData getMenu(String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(menuMapper.selectByPrimaryKey(uuid));
        return resultData;
    }

    @Override
    public ResultData editMenu(Menu menu) {
        int i = 0;
        ResultData resultData = new ResultData();
        User loginInfo = (User) request.getSession().getAttribute("userInfo");
        if (menu.getUuid() != null && !menu.getUuid().equals("")) {
            // 更新
            menu.setUpdateDate(DateUtil.currentTime());
            menu.setUpdateUser(loginInfo.getUserNm());
            if (menu.getParentId() != null && !menu.getParentId().equals("")) {
                // 有父级菜单
                menu.setLeavelId(2);
                // 判断前端传来的menu的父菜单和数据库的menu的父菜单是不是同一个
                MenuExample menuExample = new MenuExample();
                menuExample.createCriteria().andUuidEqualTo(menu.getUuid());
                Menu preMenu = menuMapper.selectByPrimaryKey(menu.getUuid());
                if (!preMenu.getParentId().equals(menu.getParentId())) {
                    // 更换了父菜单
                    MenuExample mExample = new MenuExample();
                    mExample.createCriteria().andParentIdEqualTo(menu.getParentId());
                    long l = menuMapper.countByExample(mExample);
                    menu.setSort((int) (l + 1));
                }
            } else {
                // 没有父级菜单
                menu.setLeavelId(1);
            }
            i += menuMapper.updateByPrimaryKeySelective(menu);
        } else {
            // 新增
            menu.setUuid(UUIDUtil.generateUUID());
            menu.setCreateDate(DateUtil.currentTime());
            menu.setCreateUser(loginInfo.getUserNm());
            if (menu.getParentId() != null && !"".equals(menu.getParentId())) {
                // 有父级菜单
                menu.setLeavelId(2);
                MenuExample menuExample = new MenuExample();
                menuExample.createCriteria().andParentIdEqualTo(menu.getParentId());
                // 降序查找
                menuExample.setOrderByClause("SORT DESC");
                List<Menu> menuList = menuMapper.selectByExample(menuExample);
                if (menuList.size() > 0 && !menuList.equals("")) {
                    menu.setSort(menuList.get(0).getSort() + 1);
                } else {
                    menu.setSort(1);
                }
            } else {
                // 自己是父菜单
                menu.setLeavelId(1);
                MenuExample menuExample = new MenuExample();
                menuExample.createCriteria().andParentIdEqualTo("");
                // 降序查找
                menuExample.setOrderByClause("SORT DESC");
                List<Menu> menuList = menuMapper.selectByExample(menuExample);
                if (menuList.size() > 0 && !menuList.equals("")) {
                    menu.setSort(menuList.get(0).getSort() + 1);
                } else {
                    menu.setSort(1);
                }
            }
            i += menuMapper.insertSelective(menu);
        }
        resultData.setData(i);
        return resultData;
    }
}
