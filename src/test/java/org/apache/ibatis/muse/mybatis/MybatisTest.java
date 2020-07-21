/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.mybatis;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {

    public static SqlSessionFactory sessionFactory;

    public static SqlSession getSession() {

        try {
            InputStream inputStream = new FileInputStream("/Users/muse/SourceCodes/mybatis-3-mybatis-3.4.4/src"
                    + "/test/java/org/apache/ibatis/muse/mybatis/mybatis-config.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            System.out.println(e);
        }
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        SqlSession session = getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        try {
            UserDomain userDomain = userMapper.selectById(8);
            System.out.println(userDomain);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}