package top.hapleow.common.cglibtest;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import top.hapleow.common.UserServiceImpl;

import java.lang.reflect.Method;

public class CGLibProxy {



    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //设置业务类（需要被代理的类）
        enhancer.setSuperclass(UserServiceImpl.class);
        // 设置回调方法（代理逻辑）
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置处理...");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("后置处理...");
                return result;
            }
        });

        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.deleteById(1);
    }
}
