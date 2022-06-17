package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Manufacturer;
import com.example.devedbaseproject.repository.IManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManufacturerController {
    private final IManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerController(IManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/manufacturer")
    public String findAll(Model model) {
        List<Manufacturer> manufacturer = manufacturerRepository.findAll();
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer-list";
    }

    @GetMapping("/manufacturer-create")
    public String createManufacturerForm(Manufacturer manufacturer) {
        return "manufacturer-create";
    }

    @PostMapping("/manufacturer-create")
    public String createManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturer";
    }

    @GetMapping("/manufacturer-delete/{id}")
    public String deleteManufacturer(@PathVariable("id") Long id) {
        manufacturerRepository.deleteById(id);
        return "redirect:/manufacturer";
    }

    @GetMapping("/manufacturer-update/{id}")
    public String updateManufacturerForm(@PathVariable("id") Long id, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid manufacturer ID" + id));
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer-update";
    }

    @PostMapping("/manufacturer-update")
    public String updateManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturer";
    }


}
