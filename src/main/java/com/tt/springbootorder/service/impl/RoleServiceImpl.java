package com.tt.springbootorder.service.impl;

import com.tt.springbootorder.dao.RoleMapper;
import com.tt.springbootorder.pojo.Role;
import com.tt.springbootorder.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.impl.RoleServiceImpl
 * @Description:  用户业务接口实现类
 * @Author:      Administrator
 * @CreateDate: 2018/12/7 9:25
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findRoles() throws Exception {
        return roleMapper.selectAll();
    }
}
