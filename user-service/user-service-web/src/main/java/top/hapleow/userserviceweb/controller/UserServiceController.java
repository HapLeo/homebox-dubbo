package top.hapleow.userserviceweb.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserServiceController {

    @Reference
    private IUserService userService;

    @RequestMapping("/list")
    public Object list() {
        return userService.list();
    }

    @RequestMapping("/add")
    public Object add(User user) {
        userService.addItem(user);
        return "SUCCESS";
    }

    @RequestMapping("/edit")
    public Object edit(User user) {
        userService.editItem(user);
        return "SUCCESS";
    }

}
