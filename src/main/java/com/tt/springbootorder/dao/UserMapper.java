package com.tt.springbootorder.dao;

import com.tt.springbootorder.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserAndPwd(@Param("userName") String username, @Param("userPassword") String userpassword);

    List<User> selectAll();

    Integer updateByUserName(@Param("name") String username, @Param("pwd") String newpassword);

    User selectByUserCode(@Param("userCode") String userCode);

    List<User> selectByUserNameOrRole(@Param("name")String username,@Param("userrole") String userrole);
}