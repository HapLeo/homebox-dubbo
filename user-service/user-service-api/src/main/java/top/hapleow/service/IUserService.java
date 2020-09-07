package top.hapleow.service;

import top.hapleow.model.User;

import java.util.List;

public interface IUserService {

    User getById(Long id);

    List<User> list();

    void addItem(User user);

    void editItem(User user);
}
