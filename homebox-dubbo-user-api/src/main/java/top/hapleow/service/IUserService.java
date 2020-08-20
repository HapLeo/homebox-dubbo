package top.hapleow.service;

import top.hapleow.model.User;

import java.util.List;

public interface IUserService {

    User getById(Integer id);

    List<User> list();
}
