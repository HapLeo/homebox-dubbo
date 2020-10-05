package top.hapleow.common;

public class Demo {

    public static void main(String[] args) throws Throwable {

        IUserService userService = new UserServiceImpl();

        // 普通的调用方法
        userService.deleteById(1);

        // 通过代理调用方法:先获取代理类，在调用代理的业务方法
        IUserService userServiceProxy = (IUserService) new ServiceHandler(userService).getProxy();
        userServiceProxy.deleteById(2);
    }
}
