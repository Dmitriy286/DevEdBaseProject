package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
@PreAuthorize("hasAuthority('Admin')")
public class EmployeesController {
    private final IEmployeeRepository repository;
    private final IRoleRepository roleRepository;

    @Autowired
    public EmployeesController(IEmployeeRepository repository, IRoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Employee> employeeList = repository.findAll();
        model.addAttribute("employees", employeeList);
        return "employee/showAll";
    }

    @GetMapping("{employee}")
    public String findEmployeeById(@PathVariable Employee employee, Model model) {
        model.addAttribute("employee", employee);
//        Optional<Employee> employee = repository.findById(id);
//        if (employee.isPresent()) {
//            model.addAttribute("employee", employee.get());
//            System.out.println(employee);
//        }
//        else {
//            System.out.println("Error Found");
//        }
        return "employee/employee";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        return "employee/new";
    }

    @PostMapping()
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
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
            System.out.println(employee);
            System.out.println(employee.get());
        }
        else {
            System.out.println("Error Found");
        }

        return "employee/edit";
    }

//    @PatchMapping("/{id}")
    @PostMapping("/{id}")
    public String update(@RequestParam Map<String, String> form,
            @ModelAttribute("employee") Employee employee, @PathVariable("id") Long id) {

        List<Role> roles = roleRepository.findAll();
        Set<String> stringroles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        System.out.println(roles);
        System.out.println(stringroles);
        System.out.println(employee);
        System.out.println(employee.getRoles());
        System.out.println(form);
        System.out.println(form.keySet());

        employee.getRoles().clear();

//        if (form.containsKey())

        for (String key : form.keySet()) {
            if (stringroles.contains(key)) {
                employee.getRoles().add(roleRepository.findByName(key).get());
            }
        }


        repository.save(employee);

        return "redirect:/employees";
    }

    //    @DeleteMapping("/{id}/delete")
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/employees";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam("filter") String filter, Model model) {
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