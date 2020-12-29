package top.hapleow.userserviceapp.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;
import top.hapleow.userserviceapp.dao.UserMapper;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
        if (user.getUserName() == null) {
            user = generateRandomUser();
        }
        System.out.println(user);
        userMapper.insert(user);
    }

    @Override
    public void editItem(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * 生成随机User对象
     * @return
     */
    private User generateRandomUser() {

        ThreadLocalRandom current = ThreadLocalRandom.current();

        String uuid = UUID.randomUUID().toString();
        int int4 = (int) (current.nextDouble() * 10000) + 1000;
        int int6 = (int) (current.nextDouble() * 1000000) + 100000;

        User user = new User();
        user.setUserNickName(uuid.substring(0, 6));
        user.setUserName(uuid.substring(0, 6));
        user.setUserPassword(uuid);
        user.setUserPasswordSalt(String.valueOf(int6));
        user.setUserSeqId(UUID.randomUUID().toString());
        user.setUserTelephone("1802182" + int4);
        System.out.println(user);
        return user;
    }
}
