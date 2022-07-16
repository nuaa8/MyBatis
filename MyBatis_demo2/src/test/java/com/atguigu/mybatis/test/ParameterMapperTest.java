package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {

    /**
     * MyBatis获取参数值的两种方式：${}和#{}
     * ${}本质字符串拼接
     * #{}本质占位符赋值
     *
     * ${}使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，需要  手动 加单引
     * 号；但是#{}使用占位符赋值的方式拼接sql，此时为字符串类型或日期类型的字段进行赋值时，可以 自动 添加单引号
     *
     * MyBatis获取参数值的各种情况：
     * 1、mapper接口方法的参数为 单个 的字面量 类型
     * 可以通过${}和#{}以任意的名称获取参数值，但是需要注意${}的 单引号问题
     *
     * 2、mapper接口方法的参数为 多个时
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储bi
     * a>以arg0,arg1...为键，以参数为值
     * b>以param1,param2...为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题 （这里的键是 mybatis设置的）
     *
     * 3、若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
     * 只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题  （这里的键是我们自己设置的）
     *
     * 4、mapper接口方法的参数是  实体类类型的参数   （这种情况用的比较多）
     * 只需要通过#{}和${}以  属性的方式访问属性值  即可，但是需要注意${}的单引号问题
     *
     * 5、使用@Param注解  命名参数  （主要是使用  注解）
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * 第一种>以@Param注解的值为键，以参数为值
     * 第二种>以param1,param2...为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     *
     *
     * 注意事项：
     * 1、查询的标签select必须设置属性resultType或resultMap，用于设置实体类和数据库表的映射
     * 关系
     * resultType：自动映射，用于属性名和表中字段名一致的情况
     * resultMap：自定义映射，用于一对多或多对一或字段名和属性名不一致的情况
     * 2、当查询的数据为多条时，不能使用实体类作为返回值，只能使用集合，否则会抛出异常
     * TooManyResultsException；但是若查询的数据只有一条，可以使用实体类或集合作为返回值
     */

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        //通过代理模式创建UserMapper接口的代理实现类对象
        ParameterMapper mapper=sqlSession.getMapper(ParameterMapper.class);
        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserByUsername(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("张三"); //用 张三，因为只有一条，如果用 admin,因为数据库中有多条，而接收端只有一条，所以会报错
        System.out.println(user);
    }

    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("张三", "123456");  //用 张三，因为只有一条，如果用 admin,因为数据库中有多条，而接收端只有一条，所以会报错
        System.out.println(user);
    }

    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        //因为用Map集合，所以要先创建map对象，并添加要验证的信息到map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("username", "张三");     //用 张三，因为只有一条，如果用 admin,因为数据库中有多条，而接收端只有一条，所以会报错
        map.put("password", "123456");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int result = mapper.insertUser(new User(null, "李四", "123", 23, "男", "123@qq.com"));
        System.out.println(result);
    }

    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("张三", "123456"); //用 张三，因为只有一条，如果用 admin,因为数据库中有多条，而接收端只有一条，所以会报错
        System.out.println(user);
    }
}
