//package com.example.devedbaseproject.models;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Entity
//@Table(name = "tag_list_wrapper")
//public class TagListWrapper {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//    @OneToMany(cascade = CascadeType.DETACH)
//    @JoinColumn(name = "tags")
//    private List<Tag> tags;
//
//
//
//
//}
