package com.example.devedbaseproject.models;

import java.util.ArrayList;

public class ProductWrapper {

    private ArrayList<Product> productList;

    public ProductWrapper() {
        this.productList = new ArrayList<>();
    }

    public ProductWrapper(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
