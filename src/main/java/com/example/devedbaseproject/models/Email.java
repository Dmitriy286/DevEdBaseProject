package com.example.devedbaseproject.models;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private List<Customer> customers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
    private Product product;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private List<Product> products;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "send")
    private boolean send;

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

    //region Constructors
    public Email() {
    }

    public Email(String message) {

        this.date = LocalDate.now();
        this.message = message;
        this.customer = new Customer();
        this.customers = new ArrayList<>();
        this.product = new Product();
        this.products = new ArrayList<>();
        this.employee = new Employee();
        this.send = false;
    }
    //endregion
    //region Getters,setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    //endregion
}

