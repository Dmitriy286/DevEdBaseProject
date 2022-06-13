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

    @Autowired
    public ProductParametersController(IProductParameterRepository repository,
                                       IProductParameterValueRepository ppvalueRepository,
                                       ProductRepository productRepository) {
        this.repository = repository;
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

    @PostMapping("/{id}")
    public String update(@ModelAttribute("parameter") ProductParameter parameter, @PathVariable("id") Long id) {
        repository.save(parameter);

        return "redirect:/parameters";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/parameters";
    }


}