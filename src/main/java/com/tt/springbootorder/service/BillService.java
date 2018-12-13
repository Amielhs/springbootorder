package com.tt.springbootorder.service;

import com.tt.springbootorder.pojo.Bill;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.BillService
 * @Description:  订单业务接口
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 15:05
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
public interface BillService {
    List<Bill> findAll();
}
