package top.hapleow.userserviceapp.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;
import top.hapleow.userserviceapp.dao.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {

        return userMapper.selectById(id);
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }

    @Override
    public void addItem(User user) {
        userMapper.insert(user);
    }

    @Override
    public void editItem(User user) {
        userMapper.updateById(user);
    }
}
