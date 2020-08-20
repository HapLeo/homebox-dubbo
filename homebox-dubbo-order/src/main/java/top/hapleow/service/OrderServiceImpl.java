package top.hapleow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.hapleow.model.User;

import java.util.List;

@Slf4j
public class OrderServiceImpl {

    @Autowired
    private IUserService userService;

    public void orderList() {

        List<User> list = userService.list();
        log.info("you have invoke the method: OrderService.orderList(),result is:");
        log.info(list.toString());
    }
}
