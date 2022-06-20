package com.example.devedbaseproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne(mappedBy="orderDetails")
    private Order order;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="orderdetails_product",
            joinColumns = @JoinColumn(name="orderdetails_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> productList;
}