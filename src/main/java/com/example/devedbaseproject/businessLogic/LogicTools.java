package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.businessLogic.GenericSort;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.*;
import java.util.stream.Collectors;

public class LogicTools {


    //region Sort methods for hash maps
    public static HashMap<Category, Integer> getSortedCategoryMapByValues(HashMap<Category, Integer> unsortedHashMap) {
        HashMap<Category, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        ArrayList<SortedMapClass<Category>> smc = new ArrayList<>();
        smc.add(new SortedMapClass<Category>((Category) unsortedHashMap.entrySet().toArray()[0], 5));
        smc = getSortedMap(smc);
        Category ctg = smc.get(0).getSomeClass();


        return sortedHashMap;
    }

    public static <T> ArrayList<SortedMapClass<T>> getSortedMap(ArrayList<SortedMapClass<T>> ts) {
        ts.sort(Comparator.comparingInt(r -> r.counter));
        return ts;
    }


    public static HashMap<Tag, Integer> getSortedTagMapByValues(HashMap<Tag, Integer> unsortedHashMap) {
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

    public static HashMap<ProductType, Integer> getSortedProductTypeMapByValues(HashMap<ProductType, Integer> unsortedHashMap) {
        HashMap<ProductType, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        return sortedHashMap;
    }

    public static HashMap<ProductSubtype, Integer> getSortedProductSubtypeMapByValues(HashMap<ProductSubtype, Integer> unsortedHashMap) {
        HashMap<ProductSubtype, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        return sortedHashMap;
    }
    //endregion

    //region Getters for products, types, subtypes and categories


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

    public static ArrayList<SortedMapClass<Category>> getCategories(ArrayList<SortedMapClass<ProductType>> prodTypeList) {
        ArrayList<SortedMapClass<Category>> smc = new ArrayList<>();
        for (var product : prodTypeList) {
            var prodST = getFirstOrNull(smc, product.getSomeClass().getProductType());
            if(prodST !=null) prodST.counter+= product.counter;
            else smc.add(new SortedMapClass<>(product.getSomeClass().getProductType(), product.counter));
        }
        return smc;
    }

    public static ArrayList<SortedMapClass<ProductType>> getProductTypes(ArrayList<SortedMapClass<ProductSubtype>> prodSubtypeList) {
        ArrayList<SortedMapClass<ProductType>> smc = new ArrayList<>();
        for (var productSubtype : prodSubtypeList) {
            var productType = getFirstOrNull(smc, productSubtype.getSomeClass().getProductType());
            if(productType !=null) productType.counter+= productSubtype.counter;
            else smc.add(new SortedMapClass<>(productSubtype.getSomeClass().getProductType(), productSubtype.counter));
        }
        return smc;
    }

    public static ArrayList<SortedMapClass<ProductSubtype>> getProductSubtypes2(List<Product> prodList) {
        ArrayList<SortedMapClass<ProductSubtype>> smc = new ArrayList<>();
        for (var product : prodList) {
            var prodSubtype = getFirstOrNull(smc, product.getProductSubtype());
            if(prodSubtype !=null) prodSubtype.counter++;
            else smc.add(new SortedMapClass<>(product.getProductSubtype()));
        }
        return smc;
    }

    public static <T> SortedMapClass<T> getFirstOrNull(ArrayList<SortedMapClass<T>> smc, T sc) {

        if (!smc.stream()
                .filter(r -> r.getSomeClass().equals(sc))
                .findFirst().isEmpty()) {
            return smc.stream()
                    .filter(r -> r.getSomeClass().equals(sc))
                    .findFirst().get();

        } else return null;
    }
//
//
//    public static HashMap<ProductType, Integer> getProductTypes(HashMap<ProductSubtype, Integer> prodSubtypeMap) {
//        HashMap<ProductType, Integer> productTypeHashMap = new HashMap<>();
//        for (Map.Entry<ProductSubtype, Integer> prodSubtype : prodSubtypeMap.entrySet()) {
//            ProductType productType = prodSubtype.getKey().getProductType();
//            int productTypeCounter = 1;
//            if (productTypeHashMap.containsKey(productType)) {
//                productTypeCounter += productTypeHashMap.get(productType);
//                productTypeHashMap.put(productType, productTypeCounter);
//            } else {
//                productTypeHashMap.put(productType, productTypeCounter);
//            }
//        }
//
//        return productTypeHashMap;
//    }
//
//    public static HashMap<Category, Integer> getProductCategories(HashMap<ProductType, Integer> prodTypeMap) {
//        HashMap<Category, Integer> productCategoriesMap = new HashMap<>();
//        for (Map.Entry<ProductType, Integer> prodType : prodTypeMap.entrySet()) {
//            Category category = prodType.getKey().getCategory();
//            int productCategoryCounter = 1;
//            if (productCategoriesMap.containsKey(category)) {
//                productCategoryCounter += productCategoriesMap.get(category);
//                productCategoriesMap.put(category, productCategoryCounter);
//            } else {
//                productCategoriesMap.put(category, productCategoryCounter);
//            }
//        }
//
//        return productCategoriesMap;
//    }
    //endregion


}
