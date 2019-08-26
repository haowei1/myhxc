package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@Controller
@RequestMapping(value = "/m0003")
public class MenuController {

    @Autowired
    private MenuService menuServiceImpl;

    @RequestMapping("list")
    public ResponseEntity<ResultData> showMenuList(@ModelAttribute FormInfo info) {
        return new ResponseEntity<>(menuServiceImpl.getMenuList(info.getPage(), info.getLimit()), HttpStatus.OK);
    }
}
