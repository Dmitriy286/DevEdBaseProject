package com.example.devedbaseproject.models;

import javax.persistence.*;
import java.util.List;

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

    //region Constructors
    public Customer() {
    }

    public Customer(Long id, String name, String surname, Integer age, String email, String phoneNumber, List<Order> orderList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orderList = orderList;
    }
    //endregion

    //region Getters, setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    //endregion ( (=

}