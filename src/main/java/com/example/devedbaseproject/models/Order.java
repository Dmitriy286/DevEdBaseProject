package com.example.devedbaseproject.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date")
    private String actionDateTime;

    @Column(name = "order_cost")
    private Integer orderCost;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "address")
    private String deliveryAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_details_id")
    private List<OrderDetails> orderDetails;

    //region Constructors
    public Order() {
    }

    public Order(Long id, Customer customer, Employee employee, String actionDateTime, Integer orderCost, String orderStatus, String deliveryAddress, List<OrderDetails> orderDetails) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.actionDateTime = actionDateTime;
        this.orderCost = orderCost;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.orderDetails = orderDetails;
    }
    //endregion
    //region Getters, setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getActionDateTime() {
        return actionDateTime;
    }

    public void setActionDateTime(String actionDateTime) {
        this.actionDateTime = actionDateTime;
    }

    public Integer getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Integer orderCost) {
        this.orderCost = orderCost;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
    //endregion
}
