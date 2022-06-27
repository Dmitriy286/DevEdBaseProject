package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.HashMap;
import java.util.List;

import static com.example.devedbaseproject.businessLogic.LogicTools.*;

public class CustomerGetters {

    public static HashMap<Category, Integer> getCustomerCategories(Customer customer) {
        return getProductCategories(getProductTypes(getProductSubtypes(getProductsFromCustomersOrders(customer))));
    }

    public static HashMap<ProductType, Integer> getCustomerProductType(Customer customer) {
        return getProductTypes(getProductSubtypes(getProductsFromCustomersOrders(customer)));
    }

    public static HashMap<ProductSubtype, Integer> getCustomerProductSubtype(Customer customer){
        return getProductSubtypes(getProductsFromCustomersOrders(customer));
    }
    public static List<Product> getProductsFromCustomersOrders(Customer customer) {
        List<Order> orderList = customer.getOrderList();
        return getProductList(orderList);
    }

    public static HashMap<Tag, Integer> getTagsFromCustomer(Customer customer){
        HashMap<Tag, Integer> tagMap = new HashMap<>();
        List<Tag> tagList = customer.getTagList();
        for(Tag tag : tagList){
            int tagCounter = 1;
            if(tagMap.containsKey(tag)){
                tagCounter += tagMap.get(tag);
                tagMap.put(tag,tagCounter);
            }
            else{
                tagMap.put(tag, tagCounter);
            }
        }
        return tagMap;
    }
}
