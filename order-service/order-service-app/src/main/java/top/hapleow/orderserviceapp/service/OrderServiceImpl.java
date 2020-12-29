package top.hapleow.orderserviceapp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.hapleow.model.Order;
import top.hapleow.model.User;
import top.hapleow.orderserviceapp.dao.OrderMapper;
import top.hapleow.service.IOrderService;
import top.hapleow.service.IUserService;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Reference
    private IUserService userService;

    @Override
    public void add(Order order) {
        Order newOrder = new Order();
        newOrder.setOrderNo(UUID.randomUUID().toString());
        newOrder.setOrderProductSumPrice(100.0);

        User user = userService.getById(order.getOrderUserId());

        newOrder.setOrderUserId(user.getUserId());
        newOrder.setOrderUserNickName(user.getUserNickName());

        save(newOrder);
        userService.editItem(user);
    }

    @Override
    public void edit(Order order) {

    }

    @Override
    public void delete(Long orderId) {

    }

    @Override
    public List<Order> list(Order order) {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>());
        return orders;
    }
}
