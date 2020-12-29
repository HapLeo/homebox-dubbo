package top.hapleow.orderserviceweb.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.model.Order;
import top.hapleow.service.IOrderService;

import java.util.List;

/**
 * 订单服务控制器
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private IOrderService orderService;

    @RequestMapping("/add")
    public void add(Order order) {
        orderService.add(order);
    }

    @RequestMapping("/list")
    public List<Order> list(Order order) {
        return orderService.list(order);
    }

}
