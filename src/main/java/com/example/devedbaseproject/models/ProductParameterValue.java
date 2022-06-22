package com.example.devedbaseproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product_parameter_value")
public class ProductParameterValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_parameter_value_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
//    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id")
    private ProductParameter parameter;

    @Column(name = "int_value")
    private int intValue;

    @Column(name = "string_value")
    private String stringValue;

    public ProductParameterValue(ProductParameter parameter, String s, int i) {
    }

    @Override
    public String toString() {
        return "ProductParameterValue{" +
                "id=" + id +
                ", product=" + product +
                ", parameter=" + parameter +
                ", intValue=" + intValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}

