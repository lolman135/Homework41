package com.homeworks.homework41.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_cost")
    private double totalCost;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @JsonCreator
    public Order(@JsonProperty("products") List<Product> products) {
        this.createdAt = LocalTime.now();
        this.products = products;
        this.totalCost = calculateTotalCost();
    }

    private double calculateTotalCost() {
        return products != null ? products.stream().mapToDouble(Product::getPrice).sum() : 0.0;
    }
}
