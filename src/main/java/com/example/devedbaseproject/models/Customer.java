package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="CustomerName")
    private String name;
    @Column(name="CustomerSurname")
    private String surname;
    @Column(name="CustomerAge")
    private Integer age;
    @Column(name="CustomerEmail")
    private String email;
    @Column(name="CustomerPhoneNumber")
    private String phoneNumber;

}