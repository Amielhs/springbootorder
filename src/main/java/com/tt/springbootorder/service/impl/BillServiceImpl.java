package com.tt.springbootorder.service.impl;

import com.tt.springbootorder.dao.BillMapper;
import com.tt.springbootorder.pojo.Bill;
import com.tt.springbootorder.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.impl.BillServiceImpl
 * @Description:  订单业务接口实现类
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 15:06
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> findAll() {
        return billMapper.selectAll();
    }
}
