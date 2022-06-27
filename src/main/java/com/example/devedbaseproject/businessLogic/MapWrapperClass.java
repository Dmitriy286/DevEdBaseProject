package com.example.devedbaseproject.businessLogic;

public class MapWrapperClass<T> {
    T someClass;
    Integer counter;

    public MapWrapperClass(T someClass, Integer counter) {
        this.someClass = someClass;
        this.counter = counter;
    }

    public MapWrapperClass(T smc) {
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
