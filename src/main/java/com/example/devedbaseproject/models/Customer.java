package com.example.devedbaseproject.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    
    
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phoneNumber;

}
