package com.example.devedbaseproject.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity // сущность связана с БД
@Table(name = "product") // имя, связанной таблицы
public class Product {

    public Product(Long productId, String productName, String description, Long productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.productQuantity = productQuantity;
        this.manufacturerId = new Manufacturer();
        this.parameterValues = new ArrayList<>();
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
    private Manufacturer manufacturerId; // идет в класс Manufacturer
                                        // и связывается по primary key

    @Column(name = "product_quantity")
    private Long productQuantity;

    @ManyToOne //(cascade = { CascadeType.ALL })
    @JoinColumn(name = "product_subtype_id")
    private ProductSubtype productSubtypeId;

    @OneToMany
    @JoinColumn(name="parameterValues")
    private List<ProductParameterValue> parameterValues;

}
