package com.tt.springbootorder.service;

import com.tt.springbootorder.pojo.Role;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.RoleService
 * @Description:  用户业务接口
 * @Author:      Administrator
 * @CreateDate: 2018/12/7 9:24
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
public interface RoleService {

    /**
     * @ Description:查找到所有的用户角色信息
     * @params:  * @Param:
     * @return:java.lang.String
     **/
    List<Role> findRoles() throws Exception;
}
