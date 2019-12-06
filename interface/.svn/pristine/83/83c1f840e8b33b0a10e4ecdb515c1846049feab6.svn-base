package com.qingyun;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: qingyun
 * @author: licheng
 * @create: 2019-05-20 09:24
 **/
public class TestMybatis {
    public static void main(String[] args) {
        String resource="mybatis-config.xml";
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //Object getOne = sqlSession.selectOne("getOne");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
