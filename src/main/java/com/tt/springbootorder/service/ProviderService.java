package com.tt.springbootorder.service;

import com.tt.springbootorder.pojo.Provider;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.ProviderService
 * @Description:  供应商业务接口
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 17:50
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
public interface ProviderService {
    List<Provider> findAll();
}
