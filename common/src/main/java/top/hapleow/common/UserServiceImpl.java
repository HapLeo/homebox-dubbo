package top.hapleow.common;

import lombok.Data;

@Data
public class UserServiceImpl implements IUserService {

    @Override
    public void deleteById(Integer userId) {
        System.out.println("删除了一个用户,id=" + userId);
    }
}
