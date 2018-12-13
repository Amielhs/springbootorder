package com.tt.springbootorder.controller;

import com.tt.springbootorder.pojo.Bill;
import com.tt.springbootorder.pojo.Provider;
import com.tt.springbootorder.service.BillService;
import com.tt.springbootorder.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.controller.BillController
 * @Description:  订单控制器
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 14:08
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Controller
@RequestMapping("bill")
public class BillController {

    @Autowired
    private BillService billService;


    @Autowired
    private ProviderService providerService;
    @RequestMapping("/")
    public String index(Model model) {
        List<Bill>  billList= billService.findAll();
        List<Provider>  providerList =  providerService.findAll();
        model.addAttribute("billList",billList);
        model.addAttribute("providerList",providerList);
        return "billlist";
    }
}
