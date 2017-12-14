/**
 *    Copyright 2009-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.exceptions.IbatisException;
import org.apache.ibatis.session.SqlSession;

import ognl.IntHashMap;

/**
 * Mapper代理工厂类
 *
 * @author Lasse Voss
 * @modify muse
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return methodCache;
    }

    /**
     * @SuppressWarnings("all") 告诉编译器忽略所有警告
     * @SuppressWarnings("unused") 告诉编译器针对未使用的方法或者属性警告保持静默
     * @SuppressWarnings("unchecked") 作用是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默。
     * @SuppressWarnings("deprecation") 告诉编译器忽略引用@Deprecated类所出现的警告
     * @SuppressWarnings("serial") 如果编译器出现这样的警告信息：The serializable class WmailCalendar does notdeclare a static final serialVersionUID field of type long使用这个注释将警告信息去掉。
     * 也可以使用组合模式，如下所示：
     * @SuppressWarnings("unchecked", "deprecation")
     *
     * 关键字 	                            用途
     * all 	                        to suppress all warnings
     * boxing  	                    to suppress warnings relative to boxing/unboxing operations
     * cast 	                    to suppress warnings relative to cast operations
     * dep-ann 	                    to suppress warnings relative to deprecated annotation
     * deprecation 	                to suppress warnings relative to deprecation
     * fallthrough 	                to suppress warnings relative to missing breaks in switch statements
     * finally  	                to suppress warnings relative to finally block that don’t return
     * hiding 	                    to suppress warnings relative to locals that hide variable
     * incomplete-switch 	        to suppress warnings relative to missing entries in a switch statement (enum case)
     * nls 	                        to suppress warnings relative to non-nls string literals
     * null 	                    to suppress warnings relative to null analysis
     * rawtypes 	                to suppress warnings relative to un-specific types when using generics on class params
     * restriction 	                to suppress warnings relative to usage of discouraged or forbidden references
     * serial 	                    to suppress warnings relative to missing serialVersionUID field for a serializable class
     * static-access 	            to suppress warnings relative to incorrect static access
     * synthetic-access  	        to suppress warnings relative to unoptimized access from inner classes
     * unchecked 	                to suppress warnings relative to unchecked operations
     * unqualified-field-access 	to suppress warnings relative to field access unqualified
     * unused 	                    to suppress warnings relative to unused code
     */
    @SuppressWarnings("unchecked")
    protected T newInstance(MapperProxy<T> mapperProxy) {
        String a = PkgConst.PACAKGE_CONST;
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] {mapperInterface}, mapperProxy);
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }

}
