package com.example.devedbaseproject.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Roles")
public class Role {

    public Role(){}

    public Role(Long Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

//    @ManyToMany
//    @JoinTable(name="Employees",
//            joinColumns=@JoinColumn(name="roleId"),
//            inverseJoinColumns=@JoinColumn(name="employeeId"))
//    private List<Employee> employees;

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
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

    }