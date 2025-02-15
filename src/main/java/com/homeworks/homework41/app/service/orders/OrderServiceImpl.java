package com.homeworks.homework41.app.service.orders;

import com.homeworks.homework41.app.entity.Order;
import com.homeworks.homework41.app.entity.Product;
import com.homeworks.homework41.app.repository.OrderRepository;
import com.homeworks.homework41.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Order create(List<Long> idList) {
        List<Product> products = StreamSupport
                .stream(productRepository.findAllById(idList).spliterator(), false)
                .collect(Collectors.toList());

        Order order = new Order(products);
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        return StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateById(Long id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }
}
