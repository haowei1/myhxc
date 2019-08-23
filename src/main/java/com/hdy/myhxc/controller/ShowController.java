package com.hdy.myhxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author m760384371
 * @date 2019/8/23
 */
@Controller
public class ShowController {

    /**
     * 显示主页
     * @return
     */
    @RequestMapping("/")
    public String show() {
        return "login";
    }

    /**
     * 显示详情页
     * @param page
     * @return
     */
    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        System.out.println(page);
        return page;
    }

}
