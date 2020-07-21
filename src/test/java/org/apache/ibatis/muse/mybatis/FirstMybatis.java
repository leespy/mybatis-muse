/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.reflect.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FirstMybatis {

    public static void main(String[] args) {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/Users/muse/SourceCodes/mybatis-3-mybatis-3.4"
                    + ".4/src/test/java/org/apache/ibatis/autoconstructor/mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            sqlSession.getMapper("")

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
