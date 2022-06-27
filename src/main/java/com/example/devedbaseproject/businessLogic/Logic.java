package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Logic {

//    ArrayList<MapWrapperClass<ProductSubtype>> customerSubtypes = new CustomerGetters(Customer).customerProductSubtypes;
//    ArrayList<MapWrapperClass<ProductType>> customerTypes = new CustomerGetters().customerProductTypes;
//    ArrayList<MapWrapperClass<Category>> customerCategories = new CustomerGetters().customerCategories;
//
//    ArrayList<MapWrapperClass<ProductSubtype>> subtypeFromOrders = new AllOrdersGetters().subtypesFromOrders;
//    ArrayList<MapWrapperClass<ProductType>> typeFromOrders = new AllOrdersGetters().typesFromOrders;
//    ArrayList<MapWrapperClass<Category>> categoriesFromOrders = new CustomerGetters().customerCategories;

    /*берем у клинета самые популярные категории
    из этих категорий берем самые популярные типы, далее из них подтипы

    из всех заказов берем то же самое, кроме категорий (топ категорий не берем)

    топ категорий клиента берем за основу, из них топ типов и подтипов из всех заказов
    сравниваем топ подтипы клиента и системы
    */
    public ArrayList<ProductSubtype> suggestion(Customer customer) {
        CustomerGetters customerGetters = new CustomerGetters(customer);
        ArrayList<MapWrapperClass<ProductSubtype>> subtypeWrapList = customerGetters.getCustomerProductSubtype(customer);
        ArrayList<ProductSubtype> subtypeSuggestion = new ArrayList<>();
        int length = subtypeWrapList.size();
        for (MapWrapperClass<ProductSubtype> productSubtype : subtypeWrapList) {
            ProductSubtype topSubtype = productSubtype.getSomeClass();
            subtypeSuggestion.add(topSubtype);
        }
        return subtypeSuggestion;

    }

    public void iii(Customer customer, List<Order> orderList){
        ArrayList<MapWrapperClass<Category>> customerCategories = new CustomerGetters(customer).getCustomerCategories(customer);
        ArrayList<MapWrapperClass<Category>> categoriesFromAllOrders = new AllOrdersGetters(orderList).getCategoriesFromOrders(orderList);
        ArrayList<Category> topCustomerCategories = new ArrayList<>();
        if(customerCategories.size()<=5){
            topCustomerCategories = customerCategories.get()
        }
    }

    public ArrayList<Product> suggestProducts(Customer customer, List<Order> orderList){
        ArrayList<MapWrapperClass<Category>> customerCategories = new CustomerGetters(customer).getCustomerCategories(customer);
        ArrayList<MapWrapperClass<Category>> productCategoryFromAllOrders = new AllOrdersGetters(orderList).getCategoriesFromOrders(orderList);
        ArrayList<MapWrapperClass<ProductType>> productTypeFromAllOrders = new AllOrdersGetters(orderList).getTypesFromOrders(orderList);
        for(MapWrapperClass<Category> category : customerCategories){
            p
        }
    }

}
