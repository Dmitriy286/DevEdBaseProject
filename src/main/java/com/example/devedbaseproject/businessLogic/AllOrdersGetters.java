package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.devedbaseproject.businessLogic.LogicTools.*;

public class AllOrdersGetters {

    ArrayList<MapWrapperClass<ProductSubtype>> subtypesFromOrders;
    ArrayList<MapWrapperClass<ProductType>> typesFromOrders;
    ArrayList<MapWrapperClass<Category>> categoriesFromOrders;
    ArrayList<MapWrapperClass<Tag>> tagsFromOrders;
    List<Product> productsFromOrders;

    public List<Product> getProductsFromOrders(List<Order> orderList) {
        productsFromOrders = getProductList(orderList);
        return productsFromOrders;
    }

    public ArrayList<MapWrapperClass<ProductSubtype>> getSubtypesFromOrders(List<Order> orderList) {
        subtypesFromOrders = getProductSubtypes(getProductList(orderList));
        return subtypesFromOrders;
    }

    public ArrayList<MapWrapperClass<ProductType>> getTypesFromOrders(List<Order> orderList) {
        typesFromOrders = getProductTypes(getProductSubtypes(getProductList(orderList)));
        return typesFromOrders;
    }

    public ArrayList<MapWrapperClass<Category>> getCategoriesFromOrders(List<Order> orderList) {
        categoriesFromOrders = getCategories(getProductTypes(getProductSubtypes(getProductList(orderList))));
        return categoriesFromOrders;
    }

    public ArrayList<MapWrapperClass<Tag>> getTagsFromOrders(List<Order> orderList) {
        tagsFromOrders = getTags(getProductList(orderList));
        return tagsFromOrders;
    }
}
