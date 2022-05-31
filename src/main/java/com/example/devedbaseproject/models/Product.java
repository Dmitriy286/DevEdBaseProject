package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity // сущность связана с БД
@Table(name = "products") // имя, связанной таблицы
public class Product {
    public Product(Long id) {
        this.id = id;
    }
    public Product(String productName, String country) {
        this.productName = productName;
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
