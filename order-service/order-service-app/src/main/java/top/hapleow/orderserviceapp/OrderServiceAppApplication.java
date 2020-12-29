package top.hapleow.orderserviceapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "top.hapleow.orderserviceapp.dao")
public class OrderServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceAppApplication.class, args);
    }
}
