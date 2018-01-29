/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.reflect.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Date 2018/1/29 下午3:37
 * Author lijinlong02@baidu.com
 */
public class ReflectServiceCglib implements MethodInterceptor{

    private Object target;

    public Object getProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result;
        System.out.println("CGLIBProxy before process!");
        result = methodProxy.invokeSuper(o, objects);
        System.out.println("CGLIBProxy before process!");
        return result;
    }
}
