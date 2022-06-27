package com.example.devedbaseproject.businessLogic;

import com.example.devedbaseproject.models.*;

import java.util.*;

public class Logic {
    /*берем у клинета самые популярные категории
    из этих категорий берем самые популярные типы, далее из них подтипы

    из всех заказов берем то же самое, кроме категорий (топ категорий не берем)

    топ категорий клиента берем за основу, из них топ типов и подтипов из всех заказов
    сравниваем топ подтипы клиента и системы
    */
    public Set<Product> suggestion(Customer customer, List<Order> orderList){

        return null;
    }
}
