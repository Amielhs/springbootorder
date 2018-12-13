package com.tt.springbootorder.controller;

import com.tt.springbootorder.pojo.User;
import com.tt.springbootorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: com.tt.springbootorder.controller.LoginController
 * @Description: 登录控制器
 * @Author: Administrator
 * @CreateDate: 2018/12/5 10:30
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @ Description:登录首页
     * @params: * @Param:
     * @return:java.lang.String
     **/
    @RequestMapping("/")
    public String index() {
        return "login";
    }


    /**
     * @ Description:登录
     * @params:  * @Param: user
     * @Param: request
     * @return:java.lang.String
     **/
    @RequestMapping("/doLogin")
    public String doLogin(@ModelAttribute User user, HttpServletRequest request) throws Exception {
//            System.out.println("username:" + user.getUsername());
//        if (userService.login(user.getUsername(), user.getUserpassword())) {
        User user1 = userService.login(user.getUsername(), user.getUserpassword());
        if (user1!=null) {
            request.getSession().setAttribute("user", user1);
            return "redirect:/admin/index";
        }
        return "redirect:/login/";
    }

    /**
     * @ Description: 退出登录
     * @params:  * @Param: request
     * @return:java.lang.String
     **/
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.getSession().removeAttribute("user");
        return "redirect:/login/";
    }


}
