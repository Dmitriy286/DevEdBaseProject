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
//    public Product(Long id) {
//        this.productId = productId;
//    }
    public Product( Long productId, String productName, String description, String manufacturerId, Long productQuantity, Long productSubtypeId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.productQuantity = productQuantity;
        this.productSubtypeId = productSubtypeId;
        this.manufacturerId = new Manufacturer();
//        this.ppvalue = new ProductParameterValue();
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
    private Manufacturer manufacturerId;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "product_subtype_id")
    private Long productSubtypeId;
    //не коммитить, отправить код (вместе с 30 строчкой)

//    @JoinTable(name="product_productparameters",
//            joinColumns = {@JoinColumn(name = "productId",
//                    referencedColumnName = "product_id"
//            )},
//            inverseJoinColumns = @JoinColumn(
//                    name = "productParameterId",
//                    referencedColumnName = "product_parameter_id"
//            )
//    )
//
//    @ManyToMany
//    private List<ProductParameter> parameters;
    @OneToMany
    @JoinColumn(name="parameterValues")
    private List<ProductParameterValue> parameterValues;

}
