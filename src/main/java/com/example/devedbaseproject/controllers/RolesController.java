package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Email;
import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class RolesController {
    private final IRoleRepository repository;

    @Autowired
    public RolesController(IRoleRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Role> roleList = repository.findAll();
        Collections.sort(roleList, new Comparator<Role>(){
            public int compare(Role o1, Role o2)
            {
                return o1.getId().compareTo(o2.getId());
            }
        });

        model.addAttribute("roles", roleList);
        return "role/showAll";
    }

    @GetMapping("/{name}")
    public String findRoleByName(@PathVariable("name") String name, Model model) {
        Optional<Role> role = repository.findByName(name);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
            System.out.println(role);
        }
        else {
            System.out.println("Error Found");
        }
        return "role/role";
    }

    @GetMapping("/new")
    public String newRole(@ModelAttribute("role") Role role) {
        return "role/new";
    }

    @PostMapping()
    public String createRole(@ModelAttribute("role") Role role) {
        repository.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Role> role = repository.findById(id);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
            System.out.println(role);
        }
        else {
            System.out.println("Error Found");
        }
        return "role/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("role") Role role, @PathVariable("id") Long id) {
        repository.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/roles";
    }
}