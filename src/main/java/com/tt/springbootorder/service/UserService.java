package com.tt.springbootorder.service;

import com.tt.springbootorder.pojo.User;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.UserService
 * @Description:  用户业务接口
 * @Author:      Administrator
 * @CreateDate: 2018/12/5 10:31
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
public interface UserService {

    /**
     * @ Description:用户登录
     * @params:  * @Param: username
     * @Param: userpassword
     * @return:boolean
     **/
    User login(String username, String userpassword) throws Exception;

    /**
     * @ Description:查找到所有的用户
     * @params:  * @Param:
     * @return:java.util.List<com.tt.springbootorder.pojo.User>
     **/
    List<User> findAll()throws Exception;

    /**
     * @ Description:根据实体的主键修改
     * @params:  * @Param: user
     * @return:boolean
     **/
    boolean modify(User user) throws Exception;

    /**
     * @ Description:根据用户名称修改用户信息（已作废，用于测试）
     * @params:  * @Param: username
     * @Param: newpassword
     * @return:boolean
     **/
    boolean modifyByName(String username, String newpassword)throws Exception;

    /**
     * @ Description: 通过id查找到用户
     * @params:  * @Param: id
     * @return:com.tt.springbootorder.pojo.User
     **/
    User findById(String id) throws Exception;

    /**
     * @ Description:删除某用户
     * @params:  * @Param: id
     * @return:void
     **/
    void delUser(String id)throws Exception;

    /**
     * @ Description:通过userCode查询到用户
     * @params:  * @Param: userCode
     * @return:com.tt.springbootorder.pojo.User
     **/
    User findByUserCode(String userCode)throws Exception;

    /**
     * @ Description:添加用户
     * @params:  * @Param: user
     * @return:boolean
     **/
    boolean addUser(User user)throws Exception;

    /**
     * @ Description: 通过用户名称或用户角色查询用户的信息
     * @params:  * @Param: username
     * @Param: userrole
     * @return:java.util.List<com.tt.springbootorder.pojo.User>
     **/
    List<User> findUserByuNameOrRole(String username, String userrole)throws Exception;;
}
