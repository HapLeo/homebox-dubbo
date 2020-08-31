package top.hapleow.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.hapleow.model.User;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OrderApplication {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo.xml");
        context.start();
        IUserService userService = (IUserService) context.getBean("userService");
        List<User> list = userService.list();
        list.forEach(System.out::println);

        new CountDownLatch(1).await();
    }
}
