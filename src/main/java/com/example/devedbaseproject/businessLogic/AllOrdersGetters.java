package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.HashMap;
import java.util.List;

import static com.example.devedbaseproject.businessLogic.LogicTools.*;

public class AllOrdersGetters {

    public static HashMap<Category, Integer> getGategoriesFromAllOrders(List<Order> orderList) {
        return getProductCategories(getProductTypes(getProductSubtypes(getProductsFromAllOrders(orderList))));
    }

    public static HashMap<ProductType, Integer> getProductSubtypesFromAllOrders(List<Order> orderList) {
        return getProductTypes(getProductSubtypes(getProductsFromAllOrders(orderList)));
    }

    public static HashMap<ProductSubtype, Integer> getProductTypesFromAllOrders(List<Order> orderList){
        return getProductSubtypes(getProductsFromAllOrders(orderList));
    }
    public static List<Product> getProductsFromAllOrders(List<Order> orderList) {
        return getProductList(orderList);
    }
}
