package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "productParameters")
public class ProductParameter {

    public ProductParameter(){}

    public ProductParameter(String name, boolean valueType) {
        this.name = name;
        this.valueType = valueType;
        this.ppvalue = new ProductParameterValue();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_parameter_id")
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "valueType")
    private boolean valueType;
    //false - int
    //true - String

    @ManyToOne
    @JoinColumn(name="ppvalue")
    private ProductParameterValue ppvalue;


//    @Column(name = "stringValue")
//    private String stringValue;
//
//    @Column(name = "intValue")
//    private int intValue;

//    @ManyToMany
//    @JoinTable(name="Employees",
//            joinColumns=@JoinColumn(name="roleId"),
//            inverseJoinColumns=@JoinColumn(name="employeeId"))
//    private List<Employee> employees;


    }