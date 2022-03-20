package me.paddy.lemon.aop;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProxyUtils {

    public static Object invoke(Object target, Method method, Object[] arguments) {
        try {
            return method.invoke(target, arguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AopException(e.getMessage(), e);
        }
    }

}
