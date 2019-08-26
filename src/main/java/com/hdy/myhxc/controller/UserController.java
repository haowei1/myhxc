package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.User;
import com.hdy.myhxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<ResultData> login(@ModelAttribute FormInfo info){
        User user = userServiceImpl.login(info.getForm().get("User_Code").trim(), info.getForm().get("User_Psd"));
        user.setUserPsd("");
        request.getSession().removeAttribute("userInfo");
        request.getSession().setAttribute("userInfo", user);
        ResultData resultData = new ResultData();
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    @RequestMapping("dep/list")
    public ResponseEntity<ResultData> showDepList(@ModelAttribute FormInfo info) {

        return null;
    }

    @RequestMapping("user/menu")
    public ResponseEntity<ResultData> showMenu(){
        return new ResponseEntity<>(userServiceImpl.getUserMenu(), HttpStatus.OK);
    }

    @RequestMapping("user/list")
    public ResponseEntity<ResultData> showUserList(@ModelAttribute FormInfo info) {
        System.out.println("111111111111111");
        return new ResponseEntity<>(userServiceImpl.getUserList(info.getForm().get("userNm"), info.getPage(), info.getLimit()), HttpStatus.OK);
    }

}
