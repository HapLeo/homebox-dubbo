package top.hapleow.service;

import top.hapleow.model.Order;

import java.util.List;

/**
 * 订单服务接口
 */
public interface IOrderService {

    void add(Order order);

    void edit(Order order);

    void delete(Long orderId);

    List<Order> list(Order order);
}
