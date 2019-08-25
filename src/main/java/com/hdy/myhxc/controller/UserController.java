package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.User;
import com.hdy.myhxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/23
 */
@RestController
@RequestMapping(value = "/m0001")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("user/login")
    public ResponseEntity<ResultData> login(FormInfo info){
        System.out.println(info.getForm().get("User_Code") + ":" + info.getForm().get("User_Psd"));
        User user = userServiceImpl.login(info.getForm().get("User_Code").trim(), info.getForm().get("User_Psd"));
        System.out.println(user);
        ResultData resultData = new ResultData();
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    @RequestMapping("dep/list")
    public ResponseEntity<ResultData> showList(FormInfo info) {

        return null;
    }



}
