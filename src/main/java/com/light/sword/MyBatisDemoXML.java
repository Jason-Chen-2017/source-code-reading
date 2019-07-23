package com.light.sword;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisDemoXML {
    static String resource = "mybatis-config.xml";

    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Product> productList = sqlSession.selectList("productMapper.listAll");
            System.out.println(productList);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
