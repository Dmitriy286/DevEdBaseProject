package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor


@Entity // сущность связана с БД
@Table(name = "manufacturer") // имя, связанной таблицы
public class Manufacturer {
    public Manufacturer(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    public Manufacturer(String name, Long phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;


}
