package top.hapleow.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.hapleow.model.User;

import java.util.List;

public class OrderApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo.xml");
        context.start();
        IUserService userService = (IUserService) context.getBean("userService");
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }
}
