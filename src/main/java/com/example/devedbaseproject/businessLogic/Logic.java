package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class Logic {

    public HashMap<Category, Integer> getCustomerCategories(Customer customer){
        List<Order> allCustomerOrders = customer.getOrderList();
        return getSortedMapByValues(getCategoryHashMap(allCustomerOrders));
    }
    
    public HashMap<Category, Integer> getCategoriesFromAllOrders(List<Order> allOrders){
        return getSortedMapByValues(getCategoryHashMap(allOrders));
    }

    private HashMap<Category, Integer> getCategoryHashMap(List<Order> orderList) {
        HashMap<Category, Integer> categoryMap = new HashMap<>();
        for (Order order : orderList){
            List<OrderDetails> orderDetailsList = order.getOrderDetails();
            for(OrderDetails orderDetails : orderDetailsList){
                Product product = orderDetails.getProduct();
                Category category = product.getProductSubtype()
                        .getProductType()
                        .getCategory();
                int counter = 1;
                if(categoryMap.containsKey(category)){
                    counter += categoryMap.get(category);
                    categoryMap.put(category, counter);
                }
                else{
                    categoryMap.put(category,counter);
                }
            }
        }
        return categoryMap;
    }

    private  HashMap<Category, Integer> getSortedMapByValues(HashMap<Category,Integer> unsortedHashMap){
        HashMap<Category, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2)->e1,
                                LinkedHashMap::new));

        return sortedHashMap;
    }

}
