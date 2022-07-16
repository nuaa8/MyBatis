package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     *
     * 为什么要创建这个接口哪？因为MyBatis具有面向接口的功能，然后门当我们调用接口中的方法时，他就会自动匹配一个SQL语句，并且去执行
     *
     * 1.映射文件的命名规则：
     *      标所对应的实体类的类名+Mapper.xml
     *      例如，表t_user,映射的实体类为User，所对应的映射文件为UserMapper.xml
     *      因此，一个映射文件对应一个实体类，对应一张表的操作
     *      MyBatis映射文件用于编写SQL，访问以及操作表中的数据
     *      MyBatis映射文件存放的位置是src/main/resources/mappers目录下
     *
     * MyBatis面向接口编程的两个一致：
     * 1、映射文件的namespace要和mapper接口的全类名保持一致
     * 2、映射文件中SQL语句的id要和mapper接口中的方法名一致
     *
     * 表--实体类--mapper接口--映射文件
     */

    /**
     * 添加用户信息
     *
     */
    /**
     *接口中，只有
     * 1.成员变量：只能是常量，默认 public static final
     * 2.成员方法：只能是抽象方法
     *      抽象方法：一个  没有方法体  的方法就是抽象方法， 若果类中有 抽象方法 ，则该类为抽象类。
     */
     int insertUser();

    /**
     * 修改用户信息
     */
    void updateUser();


    /**
     * 删除用户信息
     */
    void deleteUser();

    /**
     * 根据id查询用户信息
     */
    User getUserById();

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();
}
