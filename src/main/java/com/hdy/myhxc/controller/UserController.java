package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.RequestData;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.User;
import com.hdy.myhxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author m760384371
 * @date 2019/8/23
 */
@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @PostMapping("m0001/user/login")
    public ResultData login(RequestData data){
        System.out.println(data.getForm().get("User_Code") + ":" + data.getForm().get("User_Psd"));
        List<User> user = userServiceImpl.login(data.getForm().get("User_Code").trim(), data.getForm().get("User_Psd"));
        System.out.println(user);
        ResultData rd = new ResultData();
        if(user == null && user.size() <= 0) {
            rd.setCode(200);
            rd.setCount(user.size());
            rd.setMsg("失败");
            rd.setData(user);
        }
        return rd;
    }

}
