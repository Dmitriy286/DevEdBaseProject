package com.example.devedbaseproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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

    @Transient
    private String pseudoname;

    public ProductParameter(String name, boolean valueType, String pseudoname) {
        this.name = name;
        this.valueType = valueType;
    }

    public ProductParameter(String pseudoname) {
        this.name = "";
        this.valueType = false;
        this.pseudoname = pseudoname;
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