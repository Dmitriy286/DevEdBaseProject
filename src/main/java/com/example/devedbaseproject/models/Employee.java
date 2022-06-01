package com.example.devedbaseproject.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee {
    private Long ID;

    public void setID(Long id) {
        this.ID = id;
    }


    public Long getID() {
        return ID;
    }
}
