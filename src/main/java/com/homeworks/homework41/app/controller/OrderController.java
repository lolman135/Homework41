package com.homeworks.homework41.app.controller;

import com.homeworks.homework41.app.dto.response.*;
import com.homeworks.homework41.app.entity.Order;
import com.homeworks.homework41.app.service.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDtoCreateResponse> createOrder(@RequestBody List<Long> productIds) {
        Order order = orderService.create(productIds);

        return (order != null)
                ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoCreateResponse.of(true, order))
                :
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoCreateResponse.of(false, null));
    }

    @GetMapping
    public ResponseEntity<OrderDtoListResponse> getAll() {
        List<Order> orders = orderService.getAll();

        return (!orders.isEmpty())
                ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoListResponse.of(false, orders))
                :
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoListResponse.of(true, Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoByIdResponse> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);

        return (order != null)
                ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoByIdResponse.of(id, true, order))
                :
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoByIdResponse.of(id, false, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDtoUpdateResponse> updateOrderById(
            @PathVariable("id") Long id,
            @RequestBody Order order){

        Order updatedOrder = orderService.updateById(id, order);

        return (updatedOrder != null)
                ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoUpdateResponse.of(id,true, updatedOrder))
                :
                ResponseEntity.status(HttpStatus.OK)
                        .body(OrderDtoUpdateResponse.of(id,false, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDtoDeleteRequest> deleteById (@PathVariable("id") Long id){
        if (orderService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(OrderDtoDeleteRequest.of(id, true));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(OrderDtoDeleteRequest.of(id, false));
        }
    }

}
