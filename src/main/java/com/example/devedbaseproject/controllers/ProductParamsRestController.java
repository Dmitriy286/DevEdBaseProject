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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductParamsRestController {

    private final ProductRepository productRepository;
    private final IProductParameterRepository productParameterRepository;
    private final IProductParameterValueRepository ppvalueRepository;


    @Autowired
    public ProductParamsRestController(ProductRepository productRepository, IProductParameterRepository productParameterRepository, IProductParameterValueRepository ppvalueRepository){
        this.productRepository = productRepository;
        this.productParameterRepository = productParameterRepository;
        this.ppvalueRepository = ppvalueRepository;
    }


    @RequestMapping(method = RequestMethod.GET, value ="/prods/{productId}")
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
        return "prod";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/prods/{productId}")
    @ResponseBody
    public String setParameters(
//            @RequestBody
            ProductParameterValue prodparamvalue,
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

        return "redirect:/prods/" + productId;
    }


}
