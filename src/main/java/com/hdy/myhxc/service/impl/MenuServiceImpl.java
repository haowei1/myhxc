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
import org.apache.log4j.Logger;
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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuExMapper menuExMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private HttpServletRequest request;

    /**
     * log4j
     */
    private Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Override
    public ResultData getMenuList(int page, int limit) {
        ResultData resultData = new ResultData();
        // 分页插件
        PageHelper.startPage(page, limit);
        // 获取一级菜单
        List<MenuEx> menuExList1 = menuExMapper.getListForLevel1();
        logger.error("获取的menuList:" + menuExList1);
        List<MenuEx> dataList = new ArrayList<>();
        for (MenuEx temp1 : menuExList1) {
            MenuEx menuEx1 = new MenuEx();
            // 将 menuEx1 的值赋给 temp1
            BeanUtils.copyProperties(temp1, menuEx1);
            dataList.add(menuEx1);
            List<MenuEx> menuExList2 = temp1.getChildren();
            if (menuExList2.size() > 0 && menuExList2 != null) {
                for (MenuEx temp2 : menuExList2) {
                    MenuEx menuEx2=new MenuEx();
                    // 将 menuEx2 的值赋给 temp2
                    BeanUtils.copyProperties(temp2, menuEx2);
                    dataList.add(menuEx2);
                }
            }
        }
        resultData.setData(dataList);
        resultData.setCount(dataList.size());
        logger.error("resultData的值：" + resultData);
        return resultData;
    }

    @Override
    public int delMenu(String uuid) {
        int i = 0;
        i += menuMapper.deleteByPrimaryKey(uuid);
        // 判断删除的菜单有没有子菜单
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(uuid);
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        if (menuList.size() > 0 && menuList != null) {
            // 如果有子菜单 也要同时删除
            for (Menu menu : menuList) {
                // 将父菜单是删除该菜单的菜单全部删除
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
        // 根据主键查询菜单信息
        resultData.setData(menuMapper.selectByPrimaryKey(uuid));
        return resultData;
    }

    @Override
    public ResultData editMenu(Menu menu) {
        int i = 0;
        ResultData resultData = new ResultData();
        // 获取登录的用户信息
        User loginInfo = (User) request.getSession().getAttribute("userInfo");
        // 如果id不为空 则是更新操作
        if (menu.getUuid() != null && !menu.getUuid().equals("")) {
            // 更新
            menu.setUpdateDate(DateUtil.currentTime());
            menu.setUpdateUser(loginInfo.getUserNm());
            // 如果该菜单有父级菜单
            if (menu.getParentId() != null && !menu.getParentId().equals("")) {
                // 有父级菜单
                menu.setLeavelId(2);
                // 判断前端传来的menu的父菜单和数据库的menu的父菜单是不是同一个
                MenuExample menuExample = new MenuExample();
                menuExample.createCriteria().andUuidEqualTo(menu.getUuid());
                // 数据库中原来的父菜单
                Menu preMenu = menuMapper.selectByPrimaryKey(menu.getUuid());
                // 如果俩个父菜单id不一致
                if (!preMenu.getParentId().equals(menu.getParentId()) || preMenu.getParentId() != menu.getParentId()) {
                    // 更换了父菜单
                    MenuExample mExample = new MenuExample();
                    // 查询其现在的父菜单下有多少子菜单
                    mExample.createCriteria().andParentIdEqualTo(menu.getParentId());
                    long l = menuMapper.countByExample(mExample);
                    // sort = 现在的个数 + 1
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
            // 判断是否有父菜单
            if (menu.getParentId() != null && !"".equals(menu.getParentId())) {
                // 有父级菜单
                menu.setLeavelId(2);
                // 查询父菜单共有多少个子菜单
                MenuExample menuExample = new MenuExample();
                menuExample.createCriteria().andParentIdEqualTo(menu.getParentId());
                long l = menuMapper.countByExample(menuExample);
                if (l > 0) {
                    menu.setSort((int) (l + 1));
                } else {
                    menu.setSort(1);
                }
            } else {
                // 自己是父菜单
                menu.setLeavelId(1);
                MenuExample menuExample = new MenuExample();
                menuExample.createCriteria().andParentIdEqualTo("");
                // 查询共有几个父菜单
                long l = menuMapper.countByExample(menuExample);
                if (l > 0) {
                    menu.setSort((int) (l + 1));
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
