package com.tt.springbootorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: com.tt.springbootorder.controller.IndexController
 * @Description:  后台首页
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 11:21
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Controller
@RequestMapping("admin")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "common/head";
    }
}
