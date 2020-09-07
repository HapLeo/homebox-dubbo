package top.hapleow.userserviceapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "top.hapleow.userserviceapp.dao")
public class UserServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceAppApplication.class, args);
    }

}
