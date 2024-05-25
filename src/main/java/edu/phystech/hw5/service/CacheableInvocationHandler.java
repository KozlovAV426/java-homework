package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.Cacheable;
import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;



public class CacheableInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Map<Method, Map<Object, Object>> cache = new HashMap<>();
    public CacheableInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] arguments) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            Map<Object, Object> methodCache = cache.computeIfAbsent(method, k -> new HashMap<>());
            Object arg = arguments[0];
            if (methodCache.containsKey(arg)) {
                return methodCache.get(arg);
            } else {
                Object result = method.invoke(target, arguments);
                methodCache.put(arg, result);
                return result;
            }
        } else {
            return method.invoke(target, arguments);
        }
    }
}
