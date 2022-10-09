package com.example.devedbaseproject.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.*;


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

    @ManyToMany
    @JoinColumn(name = "tag_count_map")
    private List<Tag> tagList;

    //region Constructors
    public Customer() {
        this.orderList = new ArrayList<>();
        this.tagList = new ArrayList<>();
    }

    public Customer(String name, String surname, Integer age, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orderList = new ArrayList<>();
        this.tagList = new ArrayList<>();
    }
    //endregion
    //region Getters, setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    //endregion


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tagList=" + tagList +
                '}';
    }
}