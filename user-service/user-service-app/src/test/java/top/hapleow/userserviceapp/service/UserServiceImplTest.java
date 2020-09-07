package top.hapleow.userserviceapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    void getById() {
    }

    @Test
    void list() {
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    void addItem() {

        User user = new User();
        user.setUserNickName("wuyulin");
        user.setUserName("wyl");
        user.setUserPassword(UUID.randomUUID().toString());
        user.setUserPasswordSalt("320012");
        user.setUserSeqId(UUID.randomUUID().toString());
        user.setUserTelephone("18021820001");
        userService.addItem(user);
    }

    @Test
    void editItem() {
    }
}