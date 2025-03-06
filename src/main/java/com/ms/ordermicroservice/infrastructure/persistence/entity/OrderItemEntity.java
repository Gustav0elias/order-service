package com.ms.ordermicroservice.infrastructure.persistence.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="orderitem_tb")

public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public OrderItemEntity(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
