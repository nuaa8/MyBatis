package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface ParameterMapper {


    /**
     * 查询所有的员工信息
     */
    List<User> getAllUser();

    /**
     * 根据用户名查询用户信息（1、单个字面量类型的参数）
     */
    User getUserByUsername(String username);

    /**
     * 验证登录（2、多个字面量类型的参数）
     */
    User checkLogin(String username, String password);

    /**
     * 验证登录（参数为map）（3、map集合类型的参数）
     */
    User checkLoginByMap(Map<String, Object> map);

    /**
     * 添加用户信息（4、实体类类型的参数）
     */
    int insertUser(User user);

    /**
     * 验证登录（使用@Param）（5、使用@Param标识参数）
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);

}
