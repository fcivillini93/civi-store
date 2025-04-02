package com.fcivillini.store_rdbms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRdbms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRdbms order;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "product_id", nullable = false)
    private ProductRdbms product;

    @Column(nullable = false)
    private int quantity;
}

