package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author m760384371
 * @date 2019/8/27
 */
@Controller
@RequestMapping("m0009")
public class RoleController {

    @Autowired
    private RoleService roleServiceImpl;

    /**
     * 查询当前用户的权限
     * @param info
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<ResultData> getList(@ModelAttribute FormInfo info) {
        return new ResponseEntity<>(roleServiceImpl.getList(info.getForm().get("roleName"), info.getPage(), info.getLimit()), HttpStatus.OK);
    }


}
