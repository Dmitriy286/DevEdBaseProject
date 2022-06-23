package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class LogicTools {

    //TODO переписать методы для вывода сетб а не хешмапы, доставать нужные элементы неудобно. Либо Елисея спросить как сделать =)


    public static  HashMap<Category, Integer> getCustomerCategories(Customer customer){
        List<Order> allCustomerOrders = customer.getOrderList();
        return getSortedCategoryMapByValues(getCategoryHashMap(allCustomerOrders));
    }
    
    public static HashMap<Category, Integer> getCategoriesFromAllOrders(List<Order> allOrders){
        return getSortedCategoryMapByValues(getCategoryHashMap(allOrders));

    }

    public static HashMap<Tag, Integer> getCustomerTags(Customer customer){
        List<Order> allCustomerOrders = customer.getOrderList();
        return getSortedTagMapByValues(getTagHashMap(allCustomerOrders));
    }

    public static HashMap<Tag, Integer> getTagsFromAllOrders(List<Order> allOrders){
        return getSortedTagMapByValues(getTagHashMap(allOrders));
    }

    private static HashMap<Category, Integer> getCategoryHashMap(List<Order> orderList) {
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

    private static HashMap<Tag, Integer> getTagHashMap(List<Order> orderList) {
        HashMap<Tag, Integer> tagMap = new HashMap<>();
        for (Order order : orderList){
            List<OrderDetails> orderDetailsList = order.getOrderDetails();
            for(OrderDetails orderDetails : orderDetailsList){
                Product product = orderDetails.getProduct();
                List<Tag> tagsList = product.getTagsList();
                for(Tag tag : tagsList) {
                    int counter = 1;
                    if (tagMap.containsKey(tag)) {
                        counter += tagMap.get(tag);
                        tagMap.put(tag, counter);
                    } else {
                        tagMap.put(tag, counter);
                    }
                }
            }
        }
        return tagMap;
    }
    //fixme а можно вот эти два метода в один запихать? разница то в объектах только
    private static HashMap<Category, Integer> getSortedCategoryMapByValues(HashMap<Category, Integer> unsortedHashMap){
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

    private static HashMap<Tag, Integer> getSortedTagMapByValues(HashMap<Tag, Integer> unsortedHashMap){
        HashMap<Tag, Integer> sortedHashMap = unsortedHashMap.entrySet()
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
