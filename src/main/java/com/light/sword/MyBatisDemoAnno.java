package com.light.sword;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

public class MyBatisDemoAnno {

    public static void main(String[] args) {
        // 获取数据源
        DataSourceFactory dataSourceFactory = new PooledDataSourceFactory();
        Properties props = new Properties();
        props.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://127.0.0.1:3306/test");
        props.setProperty("username", "root");
        props.setProperty("password", "root");
        dataSourceFactory.setProperties(props);
        DataSource dataSource = dataSourceFactory.getDataSource();
        // 事务管理
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("dev", transactionFactory, dataSource);
        // 配置对象
        Configuration configuration = new Configuration(environment);
        // add Mapper 接口类
        configuration.addMapper(ProductMapper.class);
        // open SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 从SQL会话中获取 ProductMapper
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> productList = productMapper.findAll();
        System.out.println(productList);
    }
}
