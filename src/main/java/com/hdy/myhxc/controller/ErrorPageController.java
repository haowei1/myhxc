package com.hdy.myhxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author m760384371
 * @date 2019/8/29
 */
@Controller
public class ErrorPageController {

    /**
     * 404页面
     * @return
     */
    @RequestMapping("error-404")
    public String toPage404(){
        return "error/error-404";
    }

    /**
     * 400页面
     * @return
     */
    @RequestMapping("error-400")
    public String toPage400() {
        return "error/error-400";
    }

}
