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
@Table(name = "product_subtype")
public class ProductSubtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_subtype_name")
    private String productSubtypeName;

    @Column(name = "product_subtype_description")
    private String productSubtypeDescription;

    @ManyToOne//(cascade = { CascadeType.ALL })
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
}
