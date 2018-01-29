/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.reflect;

import java.lang.reflect.Method;

import org.apache.ibatis.muse.reflect.cglib.ReflectServiceCglib;
import org.apache.ibatis.muse.reflect.jdkproxy.HelloService;
import org.apache.ibatis.muse.reflect.jdkproxy.HelloServiceImpl;
import org.apache.ibatis.muse.reflect.jdkproxy.HelloServiceJdkProxy;
import org.junit.Test;

/**
 * 反射和代理
 *
 * Date 2018/1/26 下午7:28
 * Author lijinlong02@baidu.com
 */
public class ReflectTest {

    /** ------------------------------Java 反射------------------------------- **/
    @Test
    public void testReflection() throws Throwable {
        Object obj = Class.forName(ReflectService.class.getName()).newInstance();
        Class clazz = obj.getClass();
        Method method = clazz.getMethod("sayHello", String.class);
        method.invoke(obj, "muse");
    }

    @Test
    public void testReflection1() throws Throwable {
        Object obj = ReflectService.class.newInstance();
        Method method = obj.getClass().getMethod("sayHello", String.class);
        method.invoke(obj, "muse1");
    }

    @Test
    public void testReflection2() throws Throwable {
        Class clazz = ReflectService.class;
        Method method = clazz.getMethod("sayHello", String.class);
        method.invoke(clazz.newInstance(), "muse2");
    }

    /** ------------------------------Jdk动态代理------------------------------- **/
    @Test
    public void testJdkProxy() throws Throwable {
        HelloService helloService = new HelloServiceImpl(); // 需要被代理的对象有接口实现
        HelloService proxy = (HelloService) new HelloServiceJdkProxy().getProxy(helloService);
        proxy.sayHello("jdk");
    }

    /** ------------------------------Cglib动态代理------------------------------- **/
    @Test
    public void testCglibProxy() throws Throwable {
        ReflectService target = new ReflectService();
        ReflectServiceCglib cglib = new ReflectServiceCglib();
        ReflectService proxy = (ReflectService)cglib.getProxy(target);
        proxy.sayHello("cglib");
    }

}
