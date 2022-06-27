package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogicTools {

    public static <T> ArrayList<MapWrapperClass<T>> getSortedList(ArrayList<MapWrapperClass<T>> unsortedList) {
        unsortedList.sort(Comparator.comparingInt(r -> r.counter));
        return unsortedList;
    }

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

    public static ArrayList<MapWrapperClass<Category>> getCategories(ArrayList<MapWrapperClass<ProductType>> prodTypeList) {
        ArrayList<MapWrapperClass<Category>> categoryList = new ArrayList<>();
        for (var productType : prodTypeList) {
            var category = getFirstOrNull(categoryList, productType.getSomeClass().getCategory());
            if (category != null) category.counter += productType.counter;
            else categoryList.add(new MapWrapperClass<>(productType.getSomeClass().getCategory(), productType.counter));
        }
        return getSortedList(categoryList);
    }

    public static ArrayList<MapWrapperClass<ProductType>> getProductTypes(ArrayList<MapWrapperClass<ProductSubtype>> prodSubtypeList) {
        ArrayList<MapWrapperClass<ProductType>> productTypeList = new ArrayList<>();
        for (var productSubtype : prodSubtypeList) {
            var productType = getFirstOrNull(productTypeList, productSubtype.getSomeClass().getProductType());
            if (productType != null) productType.counter += productSubtype.counter;
            else productTypeList.add(new MapWrapperClass<>(productSubtype.getSomeClass().getProductType(), productSubtype.counter));
        }
        return getSortedList(productTypeList);
    }

    public static ArrayList<MapWrapperClass<ProductSubtype>> getProductSubtypes(List<Product> prodList) {
        ArrayList<MapWrapperClass<ProductSubtype>> productSubtypeList = new ArrayList<>();
        for (var product : prodList) {
            var prodSubtype = getFirstOrNull(productSubtypeList, product.getProductSubtype());
            if (prodSubtype != null) prodSubtype.counter++;
            else productSubtypeList.add(new MapWrapperClass<>(product.getProductSubtype()));
        }
        return getSortedList(productSubtypeList);
    }

    public static ArrayList<MapWrapperClass<Tag>> getTags(List<Product> productList){
        ArrayList<MapWrapperClass<Tag>> tagsList = new ArrayList<>();
        for(var product : productList){
            var productTagList = product.getTags();
            for(var tag : productTagList){
                var productTag = getFirstOrNull(tagsList, tag);
                if(productTag != null) productTag.counter++;
                else tagsList.add(new MapWrapperClass<>(tag));
            }
        }
        return getSortedList(tagsList);
    }

    public static <T> MapWrapperClass<T> getFirstOrNull(ArrayList<MapWrapperClass<T>> smc, T sc) {
        if (!smc.stream()
                .filter(r -> r.getSomeClass().equals(sc))
                .findFirst().isEmpty()) {
            return smc.stream()
                    .filter(r -> r.getSomeClass().equals(sc))
                    .findFirst().get();

        } else return null;
    }
}
