package me.paddy.lemon.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

public class InterceptMethodInvocation implements MethodInvocation {


    private final Object target;
    private final List<MethodInterceptor> interceptors;

    private final Object proxy;
    private final Method method;
    private final Object[] arguments;

    private int currentIndex = -1;

    public InterceptMethodInvocation(Object target, Object proxy, Method method, Object[] arguments, List<MethodInterceptor> interceptors) {
        this.target = target;
        this.proxy = proxy;
        this.method = method;
        this.arguments = arguments;
        this.interceptors = interceptors;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {

        // have gone through all the interceptors?
        if (currentIndex == this.interceptors.size() - 1) {
            return ProxyUtils.invoke(target, method, arguments);
        }

        //
        MethodInterceptor interceptor = interceptors.get(++currentIndex);

        return interceptor.invoke(this);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.method;
    }
}
