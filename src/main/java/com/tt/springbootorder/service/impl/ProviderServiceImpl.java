package com.tt.springbootorder.service.impl;

import com.tt.springbootorder.dao.ProviderMapper;
import com.tt.springbootorder.pojo.Provider;
import com.tt.springbootorder.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.impl.ProviderServiceImpl
 * @Description:  供应商业务接口实现类
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 17:51
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Service
public class ProviderServiceImpl implements ProviderService{

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> findAll() {
        return providerMapper.selectAll();
    }
}
