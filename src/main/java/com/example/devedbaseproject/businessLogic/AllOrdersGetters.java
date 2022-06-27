package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

import static com.example.devedbaseproject.businessLogic.LogicTools.*;

public class AllOrdersGetters {

    ArrayList<SortedMapClass<ProductSubtype>> subtypes;
    ArrayList<SortedMapClass<ProductType>> types;
    ArrayList<SortedMapClass<Category>> category;

    public static ArrayList<SortedMapClass> getSortedList(List<Order> orderList){

        return null;
    }


    public static HashMap<Category, Integer> getCategoriesFromAllOrders(List<Order> orderList) {
        return getSortedCategoryMapByValues(getProductCategories(getProductTypes(getProductSubtypes(getProductsFromAllOrders(orderList)))));
    }

    public static HashMap<ProductType, Integer> getProductSubtypesFromAllOrders(List<Order> orderList) {
        return getSortedProductTypeMapByValues(getProductTypes(getProductSubtypes(getProductsFromAllOrders(orderList))));
    }

    public static HashMap<ProductSubtype, Integer> getProductTypesFromAllOrders(List<Order> orderList) {
        return getSortedProductSubtypeMapByValues(getProductSubtypes(getProductsFromAllOrders(orderList)));
    }

    public static List<Product> getProductsFromAllOrders(List<Order> orderList) {
        return getProductList(orderList);
    }

    public static SortedMap<Tag, Integer> getTagsFromAllOrders(List<Order> orderList) {
        SortedMap<Tag, Integer> tagMap = new SortedMap<>();
        List<Product> productList = getProductList(orderList);
        for (Product product : productList) {
            List<Tag> tagList = product.getTags();
            for (Tag tag : tagList) {
                int tagCounter = 1;
                if (tagMap.containsKey(tag)) {
                    tagCounter += tagMap.get(tag);
                    tagMap.put(tag, tagCounter);
                } else {
                    tagMap.put(tag, tagCounter);
                }
            }
        }


        return tagMap;
    }
}
