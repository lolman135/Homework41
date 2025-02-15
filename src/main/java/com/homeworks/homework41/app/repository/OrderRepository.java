package com.homeworks.homework41.app.repository;

import com.homeworks.homework41.app.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("OrderRepository")
public interface OrderRepository extends CrudRepository<Order, Long> {
}
