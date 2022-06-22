package com.example.devedbaseproject.models;

import java.util.ArrayList;

public class PPValueWrapper {

    private ArrayList<ProductParameterValue> ppValueList;

    public PPValueWrapper() {
        this.ppValueList = new ArrayList<>();
    }

    public PPValueWrapper(ArrayList<ProductParameterValue> ppValueList) {
        this.ppValueList = ppValueList;
    }


    public void setPpValueList(ArrayList<ProductParameterValue> ppValueList) {
        this.ppValueList = ppValueList;
    }

    public ArrayList<ProductParameterValue> getPpValueList() {
        return ppValueList;
    }
}
