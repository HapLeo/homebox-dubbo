package top.hapleow.orderserviceapp.service;

import org.apache.dubbo.config.annotation.Service;
import top.hapleow.model.Order;
import top.hapleow.service.IOrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

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
        return null;
    }
}
