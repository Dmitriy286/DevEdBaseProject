package com.example.devedbaseproject.models;

import javax.persistence.*;
import java.util.List;


@Entity // сущность связана с БД
@Table(name = "manufacturer") // имя, связанной таблицы
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Long id;

    @OneToMany(mappedBy = "manufacturer") // поле класса
    private List<Product> productList;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    //region Constructors
    public Manufacturer() {
    }

    public Manufacturer(Long id, List<Product> productList, String name, Long phoneNumber, String email) {
        this.id = id;
        this.productList = productList;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    //endregion
    //region Getters, setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion
}
