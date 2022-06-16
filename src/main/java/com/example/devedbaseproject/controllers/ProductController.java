package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.ProductParameter;
import com.example.devedbaseproject.models.ProductParameterValue;
import com.example.devedbaseproject.models.ProductSubtype;
import com.example.devedbaseproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final IProductParameterRepository productParameterRepository;
    private final IProductParameterValueRepository ppvalueRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final IProductSubtypeRepository subtypeRepository;


    @Autowired
    public ProductController(ProductRepository productRepository, IProductParameterRepository productParameterRepository,
                             IProductParameterValueRepository ppvalueRepository, ManufacturerRepository manufacturerRepository,
                             IProductSubtypeRepository subtypeRepository) {
        this.productRepository = productRepository;
        this.productParameterRepository = productParameterRepository;
        this.ppvalueRepository = ppvalueRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.subtypeRepository = subtypeRepository;
    }


    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> product = productRepository.findAll();
        model.addAttribute("products", product); // attribute - "${products}"
//        return "product-list";
        return "FRONT/7";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product, Model model) {
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());
        return "product-create";
    }


    @PostMapping("/product-create")
    public String createProduct(@Valid Product product) {
//    public String createProduct(@Valid Product product, BindingResult result, Model model ){
//        if (result.hasErrors()) {
//            return "product-create";
//        }
//        var manufacturerId = manufacturerRepository.findById(product.getManufacturerId().getManufacturerId()).orElseThrow();
//        product.setManufacturerId(manufacturerId);
//
//        var subtypeId = subtypeRepository.findById(product.getProductSubtypeId().getId()).orElseThrow();
//        product.setProductSubtypeId(subtypeId);

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());
        return "product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
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
        } else {
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
        } else {
            System.out.println("Error Found, likely no parameter");
        }

        if (tempproduct.isPresent()) {
            Product product = tempproduct.get();
            prodparamvalue.setProduct(product);
            ppvalueRepository.save(prodparamvalue);
            product.getParameterValues().add(prodparamvalue);
            productRepository.save(product);
        } else {
            System.out.println("Error Found, likely no product");
        }

        return "redirect:/products/" + productId;
    }

}
