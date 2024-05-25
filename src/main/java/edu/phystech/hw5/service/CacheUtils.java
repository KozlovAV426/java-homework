package edu.phystech.hw5.service;

import java.lang.reflect.Proxy;


@SuppressWarnings("unchecked")
public class CacheUtils {
    public static <T> T getCacheProxy(Class<T> clazz, T object) {
        return (T) Proxy.newProxyInstance(
                CacheUtils.class.getClassLoader(),
                new Class[]{clazz},
                new CacheableInvocationHandler(object));
    }
}

