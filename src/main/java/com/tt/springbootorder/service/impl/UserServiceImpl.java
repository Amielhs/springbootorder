package com.tt.springbootorder.service.impl;

import com.tt.springbootorder.dao.UserMapper;
import com.tt.springbootorder.pojo.User;
import com.tt.springbootorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.service.impl.UserServiceImpl
 * @Description: 用户业务接口实现类
 * @Author: Administrator
 * @CreateDate: 2018/12/5 10:31
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(String username, String userpassword) throws Exception {
//        boolean flag = false;
//        if (userMapper.selectByUserAndPwd(username,userpassword)!=null){
//            flag = true;
//        }
//        return flag;
        return userMapper.selectByUserAndPwd(username, userpassword);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifyByName(String username, String newpassword) throws Exception {
        boolean flag = false;
        if (userMapper.updateByUserName(username, newpassword) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(String id) throws Exception {
        return userMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delUser(String id) {
        userMapper.deleteByPrimaryKey(Long.valueOf(id));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUserCode(String userCode) throws Exception {
        return userMapper.selectByUserCode(userCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addUser(User user) {
        boolean flag  =false;
        if (userMapper.insertSelective(user)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<User> findUserByuNameOrRole(String username, String userrole) throws Exception {
        return userMapper.selectByUserNameOrRole(username,userrole);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modify(User user) {
        boolean flag = false;
        if (userMapper.updateByPrimaryKeySelective(user) > 0) {
            flag = true;
        }
        return flag;
    }
}
