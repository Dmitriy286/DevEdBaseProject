package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @GetMapping("/product")
    public String findAll(Model model ){
        List<Product> product = productRepository.findAll();
        model.addAttribute("products", product); // attribute - "${products}"
        return "product-list";
    }
    @GetMapping("/product-create")
    public String createProductForm(Product product){
        return "product-create";
    }


    @PostMapping("/product-create")
    public String createProduct(Product product){
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model){
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
        return "product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product){
        productRepository.save(product);
        return "redirect:/product";
    }
}
