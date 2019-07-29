package com.tt.springbootorder.controller;

import com.github.pagehelper.PageHelper;
import com.tt.springbootorder.config.page.PageResultBean;
import com.tt.springbootorder.pojo.Provider;
import com.tt.springbootorder.pojo.Role;
import com.tt.springbootorder.pojo.SpringJSONResult;
import com.tt.springbootorder.pojo.User;
import com.tt.springbootorder.service.RoleService;
import com.tt.springbootorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.controller.UserController
 * @Description: 用户控制器
 * @Author: Administrator
 * @CreateDate: 2018/12/5 19:18
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @ Description: 跳转到用户列表页，同时对用户的信息进行分页查询
     * @params: * @Param: model
     * @Param: pageNum
     * @return:java.lang.String
     **/
    @RequestMapping("/page")
    public String index(Model model, @RequestParam(value = "pageNum") String pageNum) throws Exception {
//        List<User> userList = userService.findAll(); //没有进行分页，获取数据
        PageHelper.startPage(Integer.valueOf(pageNum), 8, "creationdate desc");
        PageResultBean<User> userPageResultBean = new PageResultBean<>(userService.findAll());
        if (pageNum == null) {
            pageNum = "1";
        }
        model.addAttribute("userPageResultBean", userPageResultBean);
        List<User> userList = userPageResultBean.getRows();

        model.addAttribute("userList", userList);
        model.addAttribute("page", userPageResultBean);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalPages", userPageResultBean.getTotal());
        model.addAttribute("pages", userPageResultBean.getPages());
        return "userlist";
    }


    /**
     * @ Description: 得到全部用户信息并进行分页处理
     * @params: * @Param:
     * @return:com.tt.springbootorder.config.page.PageResultBean<com.tt.springbootorder.pojo.User>
     **/
    @RequestMapping("/getUsers")
    @ResponseBody
    public PageResultBean<User> getUsers() throws Exception {
        PageHelper.startPage(1, 10, "creationdate desc");
        return new PageResultBean<>(userService.findAll());
    }

    /**
     * @ Description:跳转到用户密码修改页面
     * @params: * @Param:
     * @return:java.lang.String
     **/
    @RequestMapping("/pwdMod")
    public String pwdMod() throws Exception {
        return "pwdmodify";
    }

    /**
     * @ Description: 修改用户密码中对旧密码的判断（为前端的JS提供数据支持）
     * @params: * @Param: oldpassword
     * @Param: request
     * @return:com.tt.springbootorder.pojo.SpringJSONResult
     **/
    @RequestMapping(value = "/pwdModify")
    @ResponseBody
    public SpringJSONResult pwdModify(@RequestParam("oldpassword") String oldpassword,
                                      HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (oldpassword == null || oldpassword.equals("")) {//密码为空
            return SpringJSONResult.build(500, "密码为空", "error");
        } else if (!oldpassword.equals(user.getUserpassword())) {//密码不正确
            return SpringJSONResult.build(500, "密码不正确", "false");
        } else if (request.getSession().getMaxInactiveInterval() < 0) {//session过期
            return SpringJSONResult.build(500, "sessionerror", "sessionerror");
        } else {//原始密码输入正确
            return SpringJSONResult.ok("true");
        }
    }

    /**
     * @ Description:实现用户密码的修改
     * @params: * @Param: newpassword
     * @Param: request
     * @return:java.lang.String
     **/
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@RequestParam("newpassword") String newpassword, HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        user.setUserpassword(newpassword);
//        if (userService.modifyByName(user.getUsername(),newpassword)){
        if (userService.modify(user)) {
            return "redirect:/login/";
        }
        return "redirect:/admin/index";
    }

    /**
     * @ Description:跳转到修改用户的页面，同时将用户的id传过去
     * @params: * @Param: id
     * @Param: model
     * @return:java.lang.String
     **/
    @RequestMapping("/userView/{id}")
    public String userView(@PathVariable("id") String id, Model model) throws Exception {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userview";
    }

    /**
     * @ Description: 删除用户的操作
     * @params: * @Param: id
     * @return:java.lang.String
     **/
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") String id) throws Exception {
        userService.delUser(id);
        return "redirect:/user/page?pageNum=1";
    }

    /**
     * @ Description: 修改用户的界面
     * @params: * @Param: id
     * @Param: model
     * @return:java.lang.String
     **/
    @RequestMapping("/modifyUser/{id}")
    public String modifyUser(@PathVariable("id") String id, Model model) throws Exception {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "usermodify";
    }

   /* @RequestMapping("/doModifyUser")
    public String doModifyUser(@ModelAttribute User user) throws Exception {
        System.out.println(user.getBirthday());
        if (userService.modify(user)){
            return "redirect:/user/page?pageNum=1";
        }
        return "redirect:/user/page?pageNum=1";
    }*/


    /**
     * @ Description: 实现用户信息的修改
     * @params: * @Param: id
     * @Param: username
     * @Param: gender
     * @Param: birthday
     * @Param: phone
     * @Param: address
     * @Param: userrole
     * @return:java.lang.String
     **/
    @RequestMapping("/doModifyUser")
    public String doModifyUser(@RequestParam("id") Integer id, @RequestParam("username") String username,
                               @RequestParam("gender") String gender, @RequestParam("birthday") String birthday,
                               @RequestParam("phone") String phone, @RequestParam("address") String address,
                               @RequestParam("userrole") String userrole) throws Exception {
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setAddress(address);
        user.setGender(Integer.valueOf(gender));
        user.setPhone(phone);
        user.setUsername(username);
        user.setUsercode(userrole);
        if (userService.modify(user)) {
            return "redirect:/user/page?pageNum=1";
        }
        return "redirect:/user/page?pageNum=1";
    }

    /**
     * @ Description: 跳转到添加用户页面
     * @params: * @Param:
     * @return:java.lang.String
     **/
    @RequestMapping("/addUserView")
    public String addUserView() throws Exception {
        return "useradd";
    }

    @RequestMapping("/findUserCode")
    @ResponseBody
    public SpringJSONResult findUserCode(@RequestParam("userCode") String userCode) throws Exception {
        if (userService.findByUserCode(userCode) != null) {//通过userCode查询到用户，则存在此用户
            return SpringJSONResult.ok("exist");
        }
        return SpringJSONResult.build(500, "已存在", "error");
    }

    /*@RequestMapping("/doAddUser")
    public String doAddUser(@ModelAttribute User user) throws Exception {
        user.getBirthday();
        if (userService.addUser(user)){
            return "redirect:/user/page?pageNum=1";
        }
        return "redirect:/user/page?pageNum=1";
    }*/

    /**
     * @ Description:实现新用户的添加
     * @params:  * @Param: username
     * @Param: usercode
     * @Param: gender
     * @Param: birthday
     * @Param: phone
     * @Param: address
     * @Param: userrole
     * @return:java.lang.String
     **/
    @RequestMapping(value = "/doAddUser", method = RequestMethod.POST)
    public String doAddUser(@RequestParam("username") String username, @RequestParam("usercode") String usercode,
                            @RequestParam("gender") String gender, @RequestParam("birthday") String birthday,
                            @RequestParam("phone") String phone, @RequestParam("address") String address,
                            @RequestParam("userrole") String userrole) throws Exception {
        User user = new User();
        user.setUsercode(usercode);
        user.setUsername(username);
        user.setGender(Integer.valueOf(gender));
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserrole(Integer.valueOf(userrole));
        if (userService.addUser(user)) {
            return "redirect:/user/page?pageNum=1";
        }
        return "redirect:/user/page?pageNum=1";
    }


    /**
     * @ Description: 通过用户名称或用户角色查询到用户的信息
     * @params:  * @Param: username
     * @Param: userrole
     * @return:com.tt.springbootorder.config.page.PageResultBean<com.tt.springbootorder.pojo.User>
     **/
    @RequestMapping(value = "/findUserByUserNameOrUserRole", method = RequestMethod.POST)
    @ResponseBody
    public PageResultBean<User> findUserByUserNameOrUserRole( @RequestParam(value = "username",required = false) String username,
                                      @RequestParam(value = "userrole",required = false) String userrole) throws Exception {
        PageHelper.startPage(1, 8, "creationdate desc");
        return new PageResultBean<>(userService.findUserByuNameOrRole(username, userrole));
    }
}
