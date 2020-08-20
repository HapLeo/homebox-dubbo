package top.hapleow.service;

import lombok.extern.slf4j.Slf4j;
import top.hapleow.model.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserServiceImpl implements IUserService {

    @Override
    public User getById(Integer id) {

        log.info("you have invoked the method: getById(" + id + ")");
        return null;
    }

    @Override
    public List<User> list() {

        log.info("you have invoked the method: list()");
        List<User> users = new ArrayList<>();
        users.add(new User(1, "zhangsan"));
        users.add(new User(2, "lisi"));
        users.add(new User(3, "wangwu"));
        return users;
    }
}
