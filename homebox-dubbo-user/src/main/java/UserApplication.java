import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class UserApplication {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/user-provider.xml");
        context.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
