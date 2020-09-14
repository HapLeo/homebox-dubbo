package top.hapleow.userserviceapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;

import java.util.List;
import java.util.Random;
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

        String uuid = UUID.randomUUID().toString();
        int int4 = new Random(10000).nextInt();
        int int6 = new Random(1000000).nextInt();

        User user = new User();
        user.setUserNickName(uuid.substring(0, 6));
        user.setUserName(uuid.substring(0, 6));
        user.setUserPassword(uuid);
        user.setUserPasswordSalt(String.valueOf(int6));
        user.setUserSeqId(UUID.randomUUID().toString());
        user.setUserTelephone("1802182" + int4);
        userService.addItem(user);
    }

    @Test
    void editItem() {
    }
}