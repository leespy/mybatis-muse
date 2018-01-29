/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.reflect.jdkproxy;

/**
 * Date 2018/1/29 下午3:15
 * Author lijinlong02@baidu.com
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
