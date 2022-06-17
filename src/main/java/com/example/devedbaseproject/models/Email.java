package com.example.devedbaseproject.models;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@ToString
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "date")
    private String date;

    @Column(name = "message")
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "products")
    private List<Product> products;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "send")
    private boolean send;


    public Email(String date, String message) {

        this.date = date;
        this.message = message;

        this.customer = new Customer();
        this.products = new ArrayList<>();
        this.employee = new Employee();

        this.send = false;
    }

    @Override
    public String toString() {
        return "Email{" +
                "Id=" + Id +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", customer=" + customer +
                ", products=" + products +
                ", employee=" + employee +
                ", send=" + send +
                '}';
    }
}

