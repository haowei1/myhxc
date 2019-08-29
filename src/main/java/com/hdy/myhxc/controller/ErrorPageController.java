package com.hdy.myhxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author m760384371
 * @date 2019/8/29
 */
@Controller
public class ErrorPageController {

    @RequestMapping("error-404")
    public String toPage404(){
        return "error/error-404";
    }

    @RequestMapping("error-400")
    public String toPage400() {
        return "error/error-400";
    }

    @RequestMapping("error-500")
    public String toPage500() {
        return "error/error-500";
    }

}
