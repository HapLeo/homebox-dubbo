package top.hapleow.orderserviceapp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.hapleow.model.Order;
import top.hapleow.orderserviceapp.dao.OrderMapper;
import top.hapleow.service.IOrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void add(Order order) {
        System.out.println("add method");
    }

    @Override
    public void edit(Order order) {

    }

    @Override
    public void delete(Long orderId) {

    }

    @Override
    public List<Order> list(Order order) {
        return orderMapper.selectList(new QueryWrapper<Order>());
    }
}
