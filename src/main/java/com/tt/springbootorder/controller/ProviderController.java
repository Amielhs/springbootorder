package com.tt.springbootorder.controller;

import com.tt.springbootorder.pojo.Provider;
import com.tt.springbootorder.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.controller.ProviderController
 * @Description: 供应商控制器
 * @Author: Administrator
 * @CreateDate: 2018/12/5 17:43
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Controller
@RequestMapping("provider")
public class ProviderController {


    @Autowired
    private ProviderService providerService;


    @RequestMapping("/")
    public String index(Model model) {
        List<Provider> providerList = providerService.findAll();
        model.addAttribute("providerList", providerList);
        return "providerlist";
    }
}
