package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.devedbaseproject.businessLogic.LogicTools.*;

public class CustomerGetters {

    ArrayList<MapWrapperClass<Category>> customerCategories;
    ArrayList<MapWrapperClass<ProductType>> customerProductType;
    ArrayList<MapWrapperClass<ProductSubtype>> customerProductSubtype;
    ArrayList<MapWrapperClass<Tag>> customerTags;


    public ArrayList<MapWrapperClass<ProductSubtype>> getCustomerProductSubtype(Customer customer) {
        List<Order> orderList = customer.getOrderList();
        List<Product> productList = getProductList(orderList);
        customerProductSubtype = getProductSubtypes(productList);
        return customerProductSubtype;
    }

    public ArrayList<MapWrapperClass<ProductType>> getCustomerProductType(Customer customer) {
        customerProductType = getProductTypes(getProductSubtypes(getProductsFromCustomersOrders(customer)));
        return customerProductType;
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
