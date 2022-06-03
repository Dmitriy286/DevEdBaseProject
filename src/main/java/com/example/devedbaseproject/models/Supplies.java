package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "supplies")
public class Supplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplies_id")
    private Long suppliesId;

    @ManyToOne//(cascade = { CascadeType.ALL })
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturerId;

    @Column(name = "supplies_date")
    private LocalDate suppliesDate;// ввод 03.06.2022
}
