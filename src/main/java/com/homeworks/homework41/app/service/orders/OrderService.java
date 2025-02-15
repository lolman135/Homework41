package com.homeworks.homework41.app.service.orders;

import com.homeworks.homework41.app.entity.Order;
import com.homeworks.homework41.app.service.BaseService;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    @Override
    Order create(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    List<Order> getAll();

    @Override
    Order getById(Long id);

    @Override
    Order updateById(Long id, Order order);
}
