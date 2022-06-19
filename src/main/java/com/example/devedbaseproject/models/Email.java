package com.example.devedbaseproject.models;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "emails")

public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "message")
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
    private List<Product> products;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @Column(name = "send")
    private boolean send;

    public Email(String message) {

        this.date = LocalDate.now();
        this.message = message;
        this.customer = new Customer();
        this.products = new ArrayList<>();
        this.employee = new Employee();
        this.send = false;
    }
    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", customer=" + customer +
                ", products=" + products +
                ", employee=" + employee +
                ", send=" + send +
                '}';
    }
}

