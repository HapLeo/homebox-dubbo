package top.hapleow.sampleService.provider;

import top.hapleow.model.User;
import top.hapleow.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    @Override
    public User getById(Long id) {

        User user = new User();
        user.setUserName("wuyulin");
        user.setUserPassword("12344555656");
        user.setUserNickName("IRON5");
        return user;
    }

    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public void addItem(User user) {

    }

    @Override
    public void editItem(User user) {

    }
}
