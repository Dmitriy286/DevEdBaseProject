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
@RequestMapping("/parameters")
public class ProductParametersController {
    private final IProductParameterRepository repository;
    private final IProductParameterValueRepository ppvalueRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductParametersController(IProductParameterRepository repository,
                                       IProductParameterValueRepository ppvalueRepository,
                                       ProductRepository productRepository) {
        this.repository = repository;
        this.ppvalueRepository = ppvalueRepository;
        this.productRepository = productRepository;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<ProductParameter> parameters = repository.findAll();
        model.addAttribute("parameters", parameters);
        return "parameters/showAll";
    }

    @GetMapping("/{name}")
    public String findParameterByName(@PathVariable("name") String name, Model model) {

        Optional<ProductParameter> parameter = repository.findByName(name);
        if (parameter.isPresent()) {
            model.addAttribute("parameter", parameter.get());
            System.out.println(parameter);
        }
        else {
            System.out.println("Error Found");
        }
        return "parameters/parameter";
    }

    @GetMapping("/new")
    public String newParameter(@ModelAttribute("parameter") ProductParameter parameter) {
        return "parameters/new";
    }

    @PostMapping()
    public String createParameter(@ModelAttribute("parameter") ProductParameter parameter) {
        repository.save(parameter);
        return "redirect:/parameters";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<ProductParameter> parameter = repository.findById(id);
        if (parameter.isPresent()) {
            model.addAttribute("parameter", parameter.get());
            System.out.println(parameter);
        }
        else {
            System.out.println("Error Found");
        }

        return "parameters/edit";
    }

//    @PatchMapping("/{id}")
    @PostMapping("/{id}")
    public String update(@ModelAttribute("parameter") ProductParameter parameter, @PathVariable("id") Long id) {
        repository.save(parameter);

        return "redirect:/parameters";
    }

    //    @DeleteMapping("/{id}/delete")
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/parameters";
    }




    @GetMapping("/productparams/{productId}")
    public String getParameters(@PathVariable("productId") Long productId,
                            @ModelAttribute("prodparamvalue") ProductParameterValue prodparamvalue,
                            Model model) {
        List<ProductParameter> parameters = repository.findAll();
        model.addAttribute("parameters", parameters);
//        model.addAttribute("prodparamvalue", prodparamvalue);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        else {
            System.out.println("Error Found");
        }
        return "parameters/productwithparameters";
    }


    @PostMapping("/productparams/{productId}")
    public String setParameters(ProductParameterValue prodparamvalue,
                                @PathVariable("productId") Long productId) {

        Optional<Product> tempproduct = productRepository.findById(productId);

        Optional<ProductParameter> newparameter = repository.findByName(prodparamvalue.getParameter().getName());

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



        return "redirect:/parameters/productparams/" + productId;
    }


}