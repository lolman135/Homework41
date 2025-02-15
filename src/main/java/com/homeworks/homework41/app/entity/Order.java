package com.homeworks.homework41.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
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

    public Order() {
    }

    private double calculateTotalCost() {
        return products != null ? products.stream().mapToDouble(Product::getPrice).sum() : 0.0;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(totalCost, order.totalCost) == 0 && Objects.equals(id, order.id) && Objects.equals(products, order.products) && Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost, products, createdAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "createdAt=" + createdAt +
                ", id=" + id +
                ", totalCost=" + totalCost +
                ", products=" + products +
                '}';
    }
}
