package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.devedbaseproject.businessLogic.LogicTools.getCustomerTags;

public class Logic {

    public List<String> getSuggestion(Customer customer){
        Set<Tag> tags = new HashSet<>();
        HashMap<Tag, Integer> tagHash = getCustomerTags(customer);
        for(int i = 0; i < 3; i++){
            //TODO переделать категории и теги на сет наверное, а то элементы доставать неудобно
        }
        return null;
    }
}
