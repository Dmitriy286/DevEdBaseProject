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

@Entity // сущность связана с БД
@Table(name = "product") // имя, связанной таблицы
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @ManyToOne//(cascade = { CascadeType.ALL })
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer; // идет в класс Manufacturer
                                        // и связывается по primary key

    @Column(name = "product_quantity")
    private Long productQuantity;

    @ManyToOne //(cascade = { CascadeType.ALL })
    @JoinColumn(name = "product_subtype_id")
    private ProductSubtype productSubtype;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="orderdetails_product",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="orderdetails_id"))
    private List<OrderDetails> orderDetailsList;

    @OneToMany
    @JoinColumn(name="parameter_values")
    private List<ProductParameterValue> parameterValues;

}
