package top.hapleow.sampleService.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import top.hapleow.service.IUserService;

import java.util.concurrent.CountDownLatch;

public class UserProvider {

    public static void main(String[] args) throws InterruptedException {

        // 服务实现
        IUserService userService = new UserServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("sample-service");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://localhost:2180,zookeeper://localhost:2181,zookeeper://localhost:2182");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20890);
     //   protocol.setThreads(200);

        // 服务提供者暴露服务配置
        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<IUserService> service = new ServiceConfig<>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(IUserService.class);
        service.setRef(userService);
        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();

        new CountDownLatch(1).await();

    }
}
