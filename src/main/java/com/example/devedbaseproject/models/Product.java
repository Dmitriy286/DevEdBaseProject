package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity // сущность связана с БД
@Table(name = "products") // имя, связанной таблицы
public class Product {
    public Product(Long Id) {
        this.Id = Id;
    }
    public Product(String productName, String country) {
        this.productName = productName;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "productname")
    private String productName;

    @Column(name = "country")
    private String country;

//    @OneToMany (mappedBy = "product", fetch = FetchType.LAZY)
//    private Collection<OrderDetails> orderdetails;


}
