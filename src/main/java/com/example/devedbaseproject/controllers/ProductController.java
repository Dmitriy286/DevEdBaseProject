package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ProductController {

    private final IProductRepository productRepository;
    private final IProductParameterRepository productParameterRepository;
    private final IProductParameterValueRepository ppvalueRepository;
    private final IManufacturerRepository manufacturerRepository;
    private final ICategoryRepository categoryRepository;
    private final IProductTypeRepository typeRepository;
    private final IProductSubtypeRepository subtypeRepository;


    @Autowired
    public ProductController(IProductRepository productRepository, IProductParameterRepository productParameterRepository,
                             IProductParameterValueRepository ppvalueRepository, IManufacturerRepository manufacturerRepository,
                             ICategoryRepository categoryRepository, IProductTypeRepository typeRepository,
                             IProductSubtypeRepository subtypeRepository) {
        this.productRepository = productRepository;
        this.productParameterRepository = productParameterRepository;
        this.ppvalueRepository = ppvalueRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
        this.subtypeRepository = subtypeRepository;
    }


    @GetMapping("/products")
    public String findAll(Model model, Product product) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); // attribute - "${products}"

        List<ProductType> types = typeRepository.findAll();
        model.addAttribute("type", types);

        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());

        return "FRONT/products";
    }

    @PostMapping("/products")
    public String createProduct(@Valid Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product/{id}")
    public String cardProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
//        model.addAttribute("manufacturer", manufacturerRepository.findAll());
//        model.addAttribute("subtype", subtypeRepository.findAll());
        model.addAttribute("value", ppvalueRepository.findAll());
        model.addAttribute("parameters", productParameterRepository.findAll());
        return "FRONT/card-product";
    }

    @GetMapping("/product/update/{id}")
    public String updateFormProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());
        return "FRONT/product-update";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product) {
        productRepository.save(product);
        return "redirect:/product/" + id;
    }

    @GetMapping("/product/parameters/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
        List<ProductParameter> parameters = productParameterRepository.findAll();
        model.addAttribute("parameters", parameters);

        ProductParameter newPP = new ProductParameter("");

        PPValueWrapper wrapper = new PPValueWrapper();
        ProductParameterValue ppv = new ProductParameterValue();
        ppv.setParameter(newPP);
        ppv.setIntValue(0);
        ppv.setStringValue("test");
        wrapper.getPpValueList().add(ppv);
        model.addAttribute("wrapper", wrapper);

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Error Found");
        }
        return "FRONT/product-parameters";
    }

    @PostMapping("/product/parameters/{id}")
    public String setParameters(@RequestParam Map<String, String> form,
                                @ModelAttribute PPValueWrapper wrapper, Model model,
                                @PathVariable("id") Long id) {

        model.addAttribute("wrapper", wrapper);


        Optional<Product> tempproduct = productRepository.findById(id);

        Product product = new Product();
        if (tempproduct.isPresent()) {
            product = tempproduct.get();
        } else {
            System.out.println("Error Found, likely no product");
        }


        for (ProductParameterValue ppvalue: wrapper.getPpValueList()) {
            ppvalue.setProduct(product);

            Optional<ProductParameter> tempproductparameter = productParameterRepository.findByName(ppvalue.getParameter().getName());

            ProductParameter productparameter = new ProductParameter();
            if (tempproductparameter.isPresent()) {
                productparameter = tempproductparameter.get();
            } else {
                System.out.println("Error Found, likely no product");
            }
            ppvalue.setParameter(productparameter);
            ppvalueRepository.save(ppvalue);
            product.getParameterValues().add(ppvalue);
            productRepository.save(product);
//            System.out.println(ppvalue);
//            System.out.println(ppvalue.getParameter());
//            System.out.println(ppvalue.getParameter().getName());
        }
        return "redirect:/product/parameters/" + id;
    }

}
