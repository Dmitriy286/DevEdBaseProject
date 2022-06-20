package com.example.devedbaseproject.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date")
    private String actionDateTime;

    @Column(name = "order_cost")
    private Integer orderCost;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "address")
    private String deliveryAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_details_id")
    private OrderDetails orderDetails;
//    @JoinTable(name = "orders_products",
//            joinColumns = {@JoinColumn(name = "order_id",
//                    referencedColumnName = "id")},
//            inverseJoinColumns = @JoinColumn(name = "product_id",
//                    referencedColumnName = "id"))
//    @ManyToMany()
//    private List<Product> productList;

}
