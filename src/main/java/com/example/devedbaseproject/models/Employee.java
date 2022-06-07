package com.example.devedbaseproject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {

    public Employee(){}

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.active = false;

        this.name = "";
        this.email = "";
        this.phonenumber = "";
        this.photo = "";
    }

    public Employee(String name, String username, String password, String email, String phonenumber, String photo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.photo = photo;
        this.roles = new ArrayList<>();
        this.active = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "photo")
    private String photo;

    @JoinTable(
            name = "employeeRole",
            joinColumns = {@JoinColumn(
                    name = "EmployeeId",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = @JoinColumn(
                    name = "RoleId",
                    referencedColumnName = "id"
            )
    )

    @OneToMany
    private List<Role> roles;

    @OneToMany(mappedBy="id")
    private List<Order> orderList;

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}