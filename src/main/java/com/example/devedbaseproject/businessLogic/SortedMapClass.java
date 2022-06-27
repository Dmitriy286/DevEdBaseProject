package com.example.devedbaseproject.businessLogic;

import java.util.SortedMap;

public class SortedMapClass <T> {
    T someClass;
    Integer counter;

    public SortedMapClass(T someClass, Integer counter) {
        this.someClass = someClass;
        this.counter = counter;
    }

    public SortedMapClass(T smc) {
        this.someClass = smc;
        this.counter = 1;
    }

    //region Getters, setters
    public T getSomeClass() {
        return someClass;
    }

    public void setSomeClass(T someClass) {
        this.someClass = someClass;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    //endregion
}
