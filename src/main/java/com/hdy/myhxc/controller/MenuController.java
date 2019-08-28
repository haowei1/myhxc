package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.service.MenuService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@RestController
@RequestMapping(value = "/m0003")
public class MenuController {

    @Autowired
    private MenuService menuServiceImpl;

    /**
     * 获取所有菜单列表
     * @param info
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<ResultData> showMenuList(@ModelAttribute FormInfo info) {
        return new ResponseEntity<>(menuServiceImpl.getMenuList(info.getPage(), info.getLimit()), HttpStatus.OK);
    }

    /**
     * 根据id删除菜单信息
     * @param uuid
     * @return
     */
    @RequestMapping("delete/{uuid}")
    public ResponseEntity<ResultData> delMenu(@PathVariable String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(menuServiceImpl.delMenu(uuid));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 根据id获取菜单信息
     * @param uuid
     * @return
     */
    @RequestMapping("get/{uuid}")
    public ResponseEntity<ResultData> getMenu(@PathVariable String uuid) {
        return new ResponseEntity<>(menuServiceImpl.getMenu(uuid), HttpStatus.OK);
    }
}
