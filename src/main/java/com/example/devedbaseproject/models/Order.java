package com.example.devedbaseproject.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @Column(name="date")
    private String actionDateTime;

    @Column(name="order_cost")
    private Integer orderCost;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="address")
    private String deliveryAddress;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="orders_products",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="id"))
    private List<Order> orderList;

}
