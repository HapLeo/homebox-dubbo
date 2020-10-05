package top.hapleow.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceHandler implements InvocationHandler {

    private Object target;

    public ServiceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用业务逻辑类之前，做点什么...");
        Object invoke = method.invoke(target, args);
        System.out.println("调用业务逻辑类之后，做点什么...");
        return invoke;
    }

    public Object getProxy() {
        
        ClassLoader classLoader = target.getClass().getClassLoader();
        return Proxy.newProxyInstance(classLoader, target.getClass().getInterfaces(), this);
    }
}
