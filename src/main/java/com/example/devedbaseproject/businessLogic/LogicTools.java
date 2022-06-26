package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.businessLogic.GenericSort;

import java.util.*;
import java.util.stream.Collectors;

public class LogicTools {

//    private static HashMap<Category, Integer> getCategoryHashMap(List<Order> orderList) {
//        HashMap<Category, Integer> categoryMap = new HashMap<>();
//        for (Order order : orderList) {
//            List<OrderDetails> orderDetailsList = order.getOrderDetails();
//            for (OrderDetails orderDetails : orderDetailsList) {
//                Product product = orderDetails.getProduct();
//                Category category = product.getProductSubtype()
//                        .getProductType()
//                        .getCategory();
//                int counter = 1;
//                if (categoryMap.containsKey(category)) {
//                    counter += categoryMap.get(category);
//                    categoryMap.put(category, counter);
//                } else {
//                    categoryMap.put(category, counter);
//                }
//            }
//        }
//        return categoryMap;
//    }
//
//    private static HashMap<Tag, Integer> getTagHashMap(List<Order> orderList) {
//        HashMap<Tag, Integer> tagMap = new HashMap<>();
//        for (Order order : orderList) {
//            List<OrderDetails> orderDetailsList = order.getOrderDetails();
//            for (OrderDetails orderDetails : orderDetailsList) {
//                Product product = orderDetails.getProduct();
//                List<Tag> tagsList = product.getTags();
//                for (Tag tag : tagsList) {
//                    int counter = 1;
//                    if (tagMap.containsKey(tag)) {
//                        counter += tagMap.get(tag);
//                        tagMap.put(tag, counter);
//                    } else {
//                        tagMap.put(tag, counter);
//                    }
//                }
//            }
//        }
//        return tagMap;
//    }

    //fixme а можно вот эти два метода в один запихать? разница то в объектах только
    private static HashMap<Category, Integer> getSortedCategoryMapByValues(HashMap<Category, Integer> unsortedHashMap) {
        HashMap<Category, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        return sortedHashMap;
    }

    private static HashMap<Tag, Integer> getSortedTagMapByValues(HashMap<Tag, Integer> unsortedHashMap) {
        HashMap<Tag, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        return sortedHashMap;
    }

    //Prototype



    public static List<Product> getProductList(List<Order> orderList) {
        List<Product> productList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderDetails> orderDetailsList = order.getOrderDetails();
            for (OrderDetails orderDetails : orderDetailsList) {
                Product product = orderDetails.getProduct();
                productList.add(product);
            }
        }
        return productList;
    }



    public static HashMap<ProductSubtype, Integer> getProductSubtypes(List<Product> prodList) {
        HashMap<ProductSubtype, Integer> productSubtypeHashMap = new HashMap<>();
        for (Product prod : prodList) {
            ProductSubtype productSubtype = prod.getProductSubtype();
            int productSubtypeCounter = 1;
            if (productSubtypeHashMap.containsKey(productSubtype)) {
                productSubtypeCounter += productSubtypeHashMap.get(productSubtype);
                productSubtypeHashMap.put(productSubtype, productSubtypeCounter);
            } else {
                productSubtypeHashMap.put(productSubtype, productSubtypeCounter);
            }
        }

        return productSubtypeHashMap;
    }

    public static HashMap<ProductType, Integer> getProductTypes(HashMap<ProductSubtype, Integer> prodSubtypeMap) {
        HashMap<ProductType, Integer> productTypeHashMap = new HashMap<>();
        for(Map.Entry<ProductSubtype, Integer> prodSubtype : prodSubtypeMap.entrySet()){
            ProductType productType = prodSubtype.getKey().getProductType();
            int productTypeCounter = 1;
            if (productTypeHashMap.containsKey(productType)) {
                productTypeCounter += productTypeHashMap.get(productType);
                productTypeHashMap.put(productType, productTypeCounter);
            } else {
                productTypeHashMap.put(productType, productTypeCounter);
            }
        }

        return productTypeHashMap;
    }

    public static HashMap<Category, Integer> getProductCategories(HashMap<ProductType, Integer> prodTypeMap) {
        HashMap<Category, Integer> productCategoriesMap = new HashMap<>();
        for(Map.Entry<ProductType, Integer> prodType : prodTypeMap.entrySet()){
            Category category = prodType.getKey().getCategory();
            int productCategoryCounter = 1;
            if (productCategoriesMap.containsKey(category)) {
                productCategoryCounter += productCategoriesMap.get(category);
                productCategoriesMap.put(category, productCategoryCounter);
            } else {
                productCategoriesMap.put(category, productCategoryCounter);
            }
        }

        return productCategoriesMap;
    }


}
