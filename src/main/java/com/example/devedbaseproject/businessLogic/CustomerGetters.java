package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.devedbaseproject.businessLogic.LogicTools.*;

public class CustomerGetters {

    ArrayList<MapWrapperClass<Category>> customerCategories;
    ArrayList<MapWrapperClass<ProductType>> customerProductTypes;
    ArrayList<MapWrapperClass<ProductSubtype>> customerProductSubtypes;
    ArrayList<MapWrapperClass<Tag>> customerTags;
    Customer customer;

    public CustomerGetters(Customer customer) {
        this.customer=customer;
    }

    public ArrayList<MapWrapperClass<ProductSubtype>> getCustomerProductSubtype(Customer customer) {
        List<Order> orderList = customer.getOrderList();
        List<Product> productList = getProductList(orderList);
        customerProductSubtypes = getProductSubtypes(productList);
        return customerProductSubtypes;
    }

    public ArrayList<MapWrapperClass<ProductType>> getCustomerProductType(Customer customer) {
        customerProductTypes = getProductTypes(getProductSubtypes(getProductsFromCustomersOrders(customer)));
        return customerProductTypes;
    }

    public ArrayList<MapWrapperClass<Category>> getCustomerCategories(Customer customer) {
        customerCategories = getCategories(getProductTypes(getProductSubtypes(getProductsFromCustomersOrders(customer))));
        return customerCategories;
    }

    public ArrayList<MapWrapperClass<Tag>> getCustomerTags(Customer customer){
        customerTags = getTags(getProductsFromCustomersOrders(customer));
        return customerTags;
    }

    public static List<Product> getProductsFromCustomersOrders(Customer customer) {
        List<Order> orderList = customer.getOrderList();
        return getProductList(orderList);
    }
}
