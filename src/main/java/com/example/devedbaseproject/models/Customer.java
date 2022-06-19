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

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name="customer_name")
    private String name;
    @Column(name="customer_surname")
    private String surname;
    @Column(name="customer_age")
    private Integer age;
    @Column(name="customer_email")
    private String email;
    @Column(name="customer_phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Order> orderList;
}