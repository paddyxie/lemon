package me.paddy.lemon.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Proxy;
import java.util.List;

public class SimpleAop {


    public static <T> T newProxy(T source, MethodInterceptor interceptor) {
        return newProxy(source, List.of(interceptor));
    }


    @SuppressWarnings("unchecked")
    public static <T> T newProxy(T source, List<MethodInterceptor> interceptors) {

        return (T) Proxy.newProxyInstance(
                source.getClass().getClassLoader(),
                source.getClass().getInterfaces(),
                new SimpleAopProxy(source, interceptors)
        );
    }


}
