/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.reflect.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Date 2018/1/29 下午3:16
 * Author lijinlong02@baidu.com
 */
public class HelloServiceJdkProxy implements InvocationHandler{

    private Object target;

    public Object getProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println("JDKProxy before process!");
        result = method.invoke(target, args);
        System.out.println("JDKProxy after process!");
        return result;
    }
}
