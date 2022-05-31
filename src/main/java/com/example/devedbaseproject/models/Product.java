package com.example.devedbaseproject.models;

import javax.persistence.*;

@Entity // сущность связана с БД
@Table(name = "products") // имя, связанной таблицы
public class Product {
    public Product(){}
    public Product(Long id) {
        this.id = id;
    }
    public Product(String productName, String country) {
        this.productName = productName;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productname")
    private String productName;

    @Column(name = "country")
    private String country;
}
