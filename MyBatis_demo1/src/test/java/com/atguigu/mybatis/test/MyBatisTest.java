package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    /**
     * SqlSession默认不自动提交事务，若需要自动提交事务
     * 可以使用SqlSessionFactory.openSession(true);
     */

    @Test
    public void testMyBatis() throws IOException {


        //加载核心配置文件
        InputStream is= Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        //获取SqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession(true);   //事务自动提交
        //获取mapper接口对象(通过代理模式创建UserMapper接口的代理实现类对象)
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        //测试功能
        //调用UserMapper接口中的方法，就可以根据UserMapper的全类名 匹配 元素文件，通过调用的 方法名 匹配
//        映射文件中的SQL标签，并执行标签中的SQL语句

        int result=mapper.insertUser();
        System.out.println("result:"+result);

//        //提交事务(提交之后，数据库中才能更新数据)     事务手动提交
//        sqlSession.commit();

    }
    @Test
    public void testUpdate() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //通过代理模式创建UserMapper接口的代理实现类对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        /**
         * (重点，重点，重点)调用UserMapper接口中的方法，就可以根据UserMapper的全类名匹配元素文件，通过调用的方法名匹配
        映射文件中的SQL标签，并执行标签中的SQL语句
         */
        mapper.updateUser();
        mapper.deleteUser();

//        User user = mapper.getUserById();
//        System.out.println(user);

        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));
   }
}
