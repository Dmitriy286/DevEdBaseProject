package com.example.devedbaseproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "product_parameters")
public class ProductParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_parameter_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value_type")
    private boolean valueType;

    public ProductParameter(String name, boolean valueType) {
        this.name = name;
        this.valueType = valueType;
    }

    public ProductParameter(String name) {
        this.name = name;
        this.valueType = false;
    }

    @Override
    public String toString() {
        return "ProductParameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", valueType=" + valueType +
                '}';
    }
}