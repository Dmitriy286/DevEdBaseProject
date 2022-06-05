package com.example.devedbaseproject.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

    public OrderDetails(){}

    public OrderDetails(Long Id, int quantity, Product product) {
        this.Id = Id;
        this.quantity = quantity;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

//    @ManyToOne (optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="Id")
//    @Column(name = "order")
//    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId")
    private Product product;

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}