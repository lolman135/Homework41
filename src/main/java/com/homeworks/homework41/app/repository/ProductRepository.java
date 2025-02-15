package com.homeworks.homework41.app.repository;

import com.homeworks.homework41.app.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ProductRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {
}
