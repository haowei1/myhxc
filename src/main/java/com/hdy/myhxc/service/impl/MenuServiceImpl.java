package com.hdy.myhxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.mapper.MenuMapper;
import com.hdy.myhxc.mapper.ex.MenuExMapper;
import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.MenuExample;
import com.hdy.myhxc.model.ex.MenuEx;
import com.hdy.myhxc.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        i = menuMapper.deleteByPrimaryKey(uuid);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(uuid);
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        if (menuList.size() > 0 && menuList != null) {
            for (Menu menu : menuList) {
                i += menuMapper.deleteByPrimaryKey(menu.getParentId());
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
}
