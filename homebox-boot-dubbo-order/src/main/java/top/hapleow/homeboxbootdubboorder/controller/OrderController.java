package top.hapleow.homeboxbootdubboorder.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.model.User;
import top.hapleow.service.IUserService;


import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private IUserService userService;

    @RequestMapping("/list")
    public Object list() {
        List<User> list = userService.list();
        return list;
    }
}
