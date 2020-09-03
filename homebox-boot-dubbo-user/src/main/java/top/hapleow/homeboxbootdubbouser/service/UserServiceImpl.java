package top.hapleow.homeboxbootdubbouser.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Override
    public User getById(Integer id) {

        User user = new User();
        user.setId(1);
        user.setName("张三-燕子");
        log.info("homebox-boot-dubbo-user/UserServiceImpl.getById() has bean invoked.");
        return user;
    }

    @Override
    public List<User> list() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("张三-燕子");
        users.add(user);
        log.info("homebox-boot-dubbo-user/UserServiceImpl.list() has bean invoked.");

        return users;
    }
}
