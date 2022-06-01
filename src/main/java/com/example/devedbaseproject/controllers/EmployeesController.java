package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    private final IEmployeeRepository repository;

    @Autowired
    public EmployeesController(IEmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Employee> employeeList = repository.findAll();
        model.addAttribute("employees", employeeList);
        return "employee/showAll";
    }

    @GetMapping("/{id}")
    public String findEmployeeById(@PathVariable("id") Long id, Model model) {

        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            System.out.println(employee);
        }
        else {
            System.out.println("Error Found");
        }
        return "employee/employee";
    }

//    @GetMapping("/new")
//    public String newProduct(@ModelAttribute("product") ProductModel product) {
//        return "new";
//    }
//
//    @PostMapping()
//    public String createProduct(@ModelAttribute("product") ProductModel product) {
//        repository.save(product);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        Optional<ProductModel> product = repository.findById(id);
//        if (product.isPresent()) {
//            model.addAttribute("product", product.get());
//            System.out.println(product);
//        }
//        else {
//            System.out.println("Error Found");
//        }
//
//        return "product/edit";
//    }
//
////    @PatchMapping("/{id}")
//    @PostMapping("/{id}")
//    public String update(@ModelAttribute("product") ProductModel product, @PathVariable("id") Long id) {
//        repository.save(product);
//
//        return "redirect:/products";
//    }
//
//    //    @DeleteMapping("/{id}/delete")
//    @GetMapping("/{id}/delete")
//    public String delete(@PathVariable("id") Long id) {
//        repository.deleteById(id);
//        return "redirect:/products";
//    }
//

}