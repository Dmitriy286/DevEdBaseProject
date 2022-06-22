package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.PPValueWrapper;
import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.ProductParameter;
import com.example.devedbaseproject.models.ProductParameterValue;
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
    private final IProductSubtypeRepository subtypeRepository;


    @Autowired
    public ProductController(IProductRepository productRepository, IProductParameterRepository productParameterRepository,
                             IProductParameterValueRepository ppvalueRepository, IManufacturerRepository manufacturerRepository,
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
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product ID" + id));
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
//                                  @ModelAttribute("prodparamvalue") ProductParameterValue prodparamvalue,
                                  Model model) {
        List<ProductParameter> parameters = productParameterRepository.findAll();
        model.addAttribute("parameters", parameters);

        Optional<ProductParameter> pp = productParameterRepository.findById(31L);
        if (pp.isPresent()) {
            ProductParameter newPP = pp.get();
            PPValueWrapper wrapper = new PPValueWrapper();
            ProductParameterValue ppv = new ProductParameterValue();
            ppv.setParameter(newPP);
            ppv.setIntValue(0);
            ppv.setStringValue("test");
            wrapper.getPpValueList().add(ppv);
            model.addAttribute("wrapper", wrapper);
            System.out.println(wrapper);
            System.out.println(wrapper.getPpValueList());
        } else {
            System.out.println("Error Found");
        }

//        List<ProductParameterValue> prodparamvalues = new ArrayList<ProductParameterValue>();
//        model.addAttribute("prodparamvalues", prodparamvalues);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Error Found");
        }
        return "product";
    }

    @PostMapping("/products/{productId}")
    public String setParameters(@RequestParam Map<String, String> form,
                                @ModelAttribute PPValueWrapper wrapper, Model model,
                                @PathVariable("productId") Long productId) {
        System.out.println(wrapper.getPpValueList() != null ? wrapper.getPpValueList().size() : "null list");
        System.out.println("--");

        model.addAttribute("wrapper", wrapper);

        System.out.println(form);
        System.out.println(wrapper);
        System.out.println(wrapper.getPpValueList());
        System.out.println(wrapper.getPpValueList().toString());
        System.out.println(wrapper.getPpValueList().get(0).toString());
        System.out.println(wrapper.getPpValueList().size());
        System.out.println(form.keySet());

        Optional<Product> tempproduct = productRepository.findById(productId);

        Product product = new Product();
        if (tempproduct.isPresent()) {
            product = tempproduct.get();
        } else {
            System.out.println("Error Found, likely no product");
        }


        for (ProductParameterValue ppvalue: wrapper.getPpValueList()) {
            ppvalue.setProduct(product);

//            Optional<ProductParameter> parameter = productParameterRepository.findByName(ppvalue.getParameter().getName());
//            if (parameter.isPresent()) {
//                ppvalue.setParameter(parameter.get());
//            } else {
//                System.out.println("Error Found, likely no parameter");
//            }

            ppvalueRepository.save(ppvalue);
            product.getParameterValues().add(ppvalue);
            productRepository.save(product);
            System.out.println(ppvalue);
            System.out.println(ppvalue.getParameter());
            System.out.println(ppvalue.getParameter().getName());
        }
//
//
//        if (tempproduct.isPresent()) {
//            Product product = tempproduct.get();
//            prodparamvalue.setProduct(product);
//            ppvalueRepository.save(prodparamvalue);
//            product.getParameterValues().add(prodparamvalue);
//            productRepository.save(product);
//        } else {
//            System.out.println("Error Found, likely no product");
//        }

        return "redirect:/products/" + productId;
    }

}
