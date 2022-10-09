package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.Role;
import com.example.devedbaseproject.repository.ICustomerRepository;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IProductRepository;
import com.example.devedbaseproject.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
@PreAuthorize("hasAuthority('Admin')")
public class EmployeesController {
    private final IEmployeeRepository repository;
    private final IRoleRepository roleRepository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;

    @Autowired
    public EmployeesController(IEmployeeRepository repository, IRoleRepository roleRepository, ICustomerRepository customerRepository, IProductRepository productRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Employee> employeeList = repository.findAll();
        Collections.sort(employeeList, new Comparator<Employee>(){
            public int compare(Employee o1, Employee o2)
            {
                return o1.getId().compareTo(o2.getId());
            }
        });
        model.addAttribute("employees", employeeList);
        return "employee/showAll";
    }

    @GetMapping("{employee}")
    public String findEmployeeById(@PathVariable Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "employee/employee";
    }
    @GetMapping("/account")
    public String showEmployeeAccount(@AuthenticationPrincipal Employee employeeAccount, Model model){
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();

        model.addAttribute("employeeAccount", employeeAccount);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);

        return "employee/employees-account";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        return "employee/new";
    }

    @PostMapping//("/employees")
    public String createEmployee(Employee employee) {
        repository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
        }
        else {
            System.out.println("Error Found");
        }

        return "employee/employee-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@RequestParam Map<String, String> form,
            @ModelAttribute("employee") Employee employee, @PathVariable("id") Long id) {

        List<Role> roles = roleRepository.findAll();
        Set<String> stringroles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        employee.getRoles().clear();

        for (String key : form.keySet()) {
            if (stringroles.contains(key)) {
                employee.getRoles().add(roleRepository.findByName(key).get());
            }
        }
        repository.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/employees";
    }

    @PostMapping("/filter")
    public String filterByName(@RequestParam("filter") String filter, Model model) {
        Iterable<Employee> employees;
        if (filter != null && !filter.isEmpty()) {
            employees = repository.findByName(filter);
        }
        else {
            employees = repository.findAll();
        }
        model.addAttribute("employees", employees);
        return "employee/showAll";
    }

}