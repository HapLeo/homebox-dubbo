package top.hapleow.userserviceapp.service;

import org.apache.dubbo.config.annotation.Service;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public User getById(Long id) {

        User user = new User();
        user.setUserId(1);
        user.setUserNickName("法海");
        return user;
    }

    @Override
    public List<User> list() {
        List<User> users = new ArrayList<>();
        users.add(getById(1L));
        return users;
    }
}
