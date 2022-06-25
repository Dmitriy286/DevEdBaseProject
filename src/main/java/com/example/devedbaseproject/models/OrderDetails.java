package com.example.devedbaseproject.models;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "product_amount")
    private Integer productAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    //region Constructors
    public OrderDetails() {
    }

    public OrderDetails(Long id, Integer productAmount, Product product) {
        Id = id;
        this.productAmount = productAmount;
        this.product = product;
    }
    //endregion
    //region Getters, setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    //endregion
}