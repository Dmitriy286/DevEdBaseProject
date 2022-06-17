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
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_type_name")
    private String productTypeName;

    @Column(name = "product_type_description")
    private String productTypeDescription;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
