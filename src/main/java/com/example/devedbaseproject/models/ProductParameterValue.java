package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Product_Parameter_Value")
public class ProductParameterValue {


    public ProductParameterValue() {
    }

    public ProductParameterValue(int intValue, String stringValue) {
        this.products = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_parameter_value_id")
    private Long Id;

    @OneToMany(mappedBy = "ppvalue")
    private List<Product> products;

    @OneToMany(mappedBy = "ppvalue")
    private List<ProductParameter> parameters;

    @Column(name = "intValue")
    private int intValue;

    @Column(name = "stringValue")
    private String stringValue;
}
