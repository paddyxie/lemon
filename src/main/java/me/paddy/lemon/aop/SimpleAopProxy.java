package me.paddy.lemon.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class SimpleAopProxy implements InvocationHandler {

    private final Object target;
    private final List<MethodInterceptor> interceptors;

    public SimpleAopProxy(Object target, List<MethodInterceptor> interceptors) {
        this.target = target;
        this.interceptors = interceptors;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // we should only intercept method annotated with @Intercept
        // usually AOP works with a method matcher, but that make the implementation too complex
        // Simple AOP only want to resolve some simple case, for more complex case, user should
        // consider Guice or Spring AOP
        Annotation declaredAnnotation = method.getAnnotation(Intercept.class);

        if(declaredAnnotation == null) {
            return ProxyUtils.invoke(target, method, args);
        }

        InterceptMethodInvocation invocation = new InterceptMethodInvocation(target, proxy, method, args, interceptors);
        return invocation.proceed();

    }

}
