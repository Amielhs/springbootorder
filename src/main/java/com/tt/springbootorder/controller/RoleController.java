package com.tt.springbootorder.controller;

import com.tt.springbootorder.pojo.Role;
import com.tt.springbootorder.service.RoleService;
import com.tt.springbootorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.controller.RoleController
 * @Description:  角色控制器
 * @Author:      Administrator
 * @CreateDate: 2018/12/7 9:22
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findUserRoles")
    @ResponseBody
    public List<Role> findUserRoles() throws Exception{
        return roleService.findRoles();
    }
}
