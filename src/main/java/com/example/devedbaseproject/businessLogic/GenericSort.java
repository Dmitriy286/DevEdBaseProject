package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.Category;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericSort<T>{

    T object;

    public GenericSort(T object) {
        this.object = object;
    }

    public HashMap<T, Integer> getSortedMap(HashMap<T, Integer> unsortedHashMap) {
        HashMap<T, Integer> sortedHashMap = unsortedHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        return sortedHashMap;
    }
}
