package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.Tag;
import com.example.devedbaseproject.repository.ICustomerRepository;
import com.example.devedbaseproject.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;

    @Autowired
    public CustomerController(ICustomerRepository customerRepository, IProductRepository productRepository) {

        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/customers")
    public String findAll(Model model ){
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customer/customers-list";
    }

    @PostMapping("/customers/filter")
    public String search(@RequestParam("filter") String filter, Model model, @AuthenticationPrincipal Employee employeeAccount) {
        Iterable<Customer> customers;
        if (filter != null && !filter.isEmpty()) {
            customers = customerRepository.findByName(filter);
        }
        else {
            customers = customerRepository.findAll();
        }

        List<Product> products = productRepository.findAll();

        model.addAttribute("employeeAccount", employeeAccount);
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);
        return "customer/card-customer";
    }
    @GetMapping("customers/{id}")
    public String findCustomer(@PathVariable Long id, Model model ){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customer);
        List<Tag> tagList = new ArrayList<Tag>(customer.getTagList());
        model.addAttribute("tagList", tagList);

        return "customer/card-customer";
    }
    @GetMapping("/customer-create")
    public String createCustomerForm(Customer customer){

        return "customer/customer-create";
    }


    @PostMapping("/customer-create")
    public String createCustomer(Customer customer){
        customerRepository.save(customer);
        return "redirect:/employees/account";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id){
        customerRepository.deleteById(id);
        return "redirect:/customers/customers-list";
    }

    @GetMapping("/customers/update/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customer);
        return "customer/customer-update";
    }

    @PostMapping("customers/update/{id}")
    public String updateCustomer(Customer customer, @PathVariable("id") Long id){

        customerRepository.save(customer);

        return "redirect:/customers/"+ id;
    }

}
