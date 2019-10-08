package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.User;
import com.hdy.myhxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
     * 用户登录
     * @param info
     * @return
     */
    @PostMapping("user/login")
    public ResponseEntity<ResultData> login(@ModelAttribute FormInfo info){
        User user = userServiceImpl.login(info.getForm().get("User_Code").trim(), info.getForm().get("User_Psd"));
        user.setUserPsd("");
        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
        session.setAttribute("userInfo", user);
        session.setMaxInactiveInterval(60*60);
        ResultData resultData = new ResultData();
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 获取左侧菜单信息
     * @return
     */
    @RequestMapping("user/menu")
    public ResponseEntity<ResultData> showMenu(){
        return new ResponseEntity<>(userServiceImpl.getUserMenu(), HttpStatus.OK);
    }

    /**
     * 获取所有用户信息
     * @param info
     * @return
     */
    @RequestMapping("user/list")
    public ResponseEntity<ResultData> showUserList(@ModelAttribute FormInfo info) {
        return new ResponseEntity<>(userServiceImpl.getUserList(info.getForm().get("userNm"), info.getPage(), info.getLimit()), HttpStatus.OK);
    }

    /**
     * 编辑用户信息，可新增或修改
     * @param user
     * @return
     */
    @RequestMapping("user/edit")
    public ResponseEntity<ResultData> editUser(User user) {
        ResultData resultData = new ResultData();
        resultData.setData(userServiceImpl.editUser(user));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 获取当前用户的权限
     * @param uuid
     * @return
     */
    @RequestMapping("user/get/{uuid}")
    public ResponseEntity<ResultData> getUserRole(@PathVariable("uuid") String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(userServiceImpl.getUserRole(uuid));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 根据id删除一个用户
     * @param uuid
     * @return
     */
    @RequestMapping("user/delete/{uuid}")
    public ResponseEntity<ResultData> delUser(@PathVariable("uuid") String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(userServiceImpl.delUser(uuid));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 根据id数组删除多个用户
     * @param info
     * @return
     */
    @RequestMapping("list/delete")
    public ResponseEntity<ResultData> delUsers(@ModelAttribute FormInfo info) {
        ResultData resultData = new ResultData();
        resultData.setData(userServiceImpl.delUsers(info.getUuids()));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 初始化密码
     * @param info
     * @return
     */
    @RequestMapping("user/initpw")
    public ResponseEntity<ResultData> initPwd(@ModelAttribute FormInfo info) {
        ResultData resultData = new ResultData();
        resultData.setData(userServiceImpl.initPwd(info.getUuid()));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

}
