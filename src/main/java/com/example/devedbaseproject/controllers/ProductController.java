package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.ProductParameter;
import com.example.devedbaseproject.models.ProductParameterValue;
import com.example.devedbaseproject.repository.IProductParameterRepository;
import com.example.devedbaseproject.repository.IProductParameterValueRepository;
import com.example.devedbaseproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final IProductParameterRepository productParameterRepository;
    private final IProductParameterValueRepository ppvalueRepository;


    @Autowired
    public ProductController(ProductRepository productRepository, IProductParameterRepository productParameterRepository, IProductParameterValueRepository ppvalueRepository){
        this.productRepository = productRepository;
        this.productParameterRepository = productParameterRepository;
        this.ppvalueRepository = ppvalueRepository;
    }


    @GetMapping("/products")
    public String findAll(Model model ){
        List<Product> product = productRepository.findAll();
        model.addAttribute("products", product); // attribute - "${products}"
        return "product-list";
    }

//    @GetMapping("/products/{productId}")
//    public String findProductById(@PathVariable("productId") Long productId, Model model,
//                                  @ModelAttribute("prodparamvalue") ProductParameterValue prodparamvalue) {
//        List<ProductParameter> parameters = productParameterRepository.findAll();
//        model.addAttribute("parameters", parameters);
//        Optional<Product> product = productRepository.findById(productId);
//        if (product.isPresent()) {
//            model.addAttribute("product", product.get());
//            System.out.println(product);
//        }
//        else {
//            System.out.println("Error Found");
//        }
//        return "product";
//    }

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

    @GetMapping("/products/{productId}")
    public String findProductById(@PathVariable("productId") Long productId,
                                  @ModelAttribute("prodparamvalue") ProductParameterValue prodparamvalue,
                                  Model model) {
        List<ProductParameter> parameters = productParameterRepository.findAll();
        model.addAttribute("parameters", parameters);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        else {
            System.out.println("Error Found");
        }
        return "product";
    }

    @PostMapping("/products/{productId}")
    public String setParameters(ProductParameterValue prodparamvalue,
                                @PathVariable("productId") Long productId) {

        Optional<Product> tempproduct = productRepository.findById(productId);

        Optional<ProductParameter> newparameter = productParameterRepository.findByName(prodparamvalue.getParameter().getName());

        if (newparameter.isPresent()) {
            prodparamvalue.setParameter(newparameter.get());
        }
        else {
            System.out.println("Error Found, likely no parameter");
        }

        if (tempproduct.isPresent()) {
            Product product = tempproduct.get();
            prodparamvalue.setProduct(product);
            ppvalueRepository.save(prodparamvalue);
            product.getParameterValues().add(prodparamvalue);
            productRepository.save(product);
        }
        else {
            System.out.println("Error Found, likely no product");
        }

        return "redirect:/products/" + productId;
    }

}
