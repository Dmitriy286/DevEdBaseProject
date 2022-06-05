package com.example.devedbaseproject.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor


@Entity // сущность связана с БД
@Table(name = "product") // имя, связанной таблицы
public class Product {
//    public Product(Long id) {
//        this.productId = productId;
//    }
    public Product( Long productId, String productName, String description, String manufacturerId, Long productQuantity, Long productSubtypeId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.productQuantity = productQuantity;
        this.productSubtypeId = productSubtypeId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @ManyToOne//(cascade = { CascadeType.ALL })
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturerId;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "product_subtype_id")
    private Long productSubtypeId;
}
