package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private final IEmployeeRepository repository;
    private final IRoleRepository rolerepo;

    public RegistrationController(IEmployeeRepository repository, IRoleRepository rolerepo) {
        this.repository = repository;
        this.rolerepo = rolerepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addEmployee(Employee employee, Model model) {
        Employee employeeFromDB = repository.findByUsername(employee.getUsername());

        if (employeeFromDB != null) {
            model.addAttribute("message", "User exists!");
            return "FRONT/registration";
        }
        Employee newemployee = new Employee(employee.getUsername(), employee.getPassword());

        Role role = rolerepo.findByName("Admin").orElseThrow();

        newemployee.getRoles().add(role);
//        rolelist.add(role);
        newemployee.setActive(true);
//        employee.setRoles(rolelist);
        repository.save(newemployee);

        return "redirect:/login";
    }
}
