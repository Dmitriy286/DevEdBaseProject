package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.Supplies;
import com.example.devedbaseproject.repository.IManufacturerRepository;
import com.example.devedbaseproject.repository.ISuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SuppliesController {
    private final ISuppliesRepository suppliesRepository;
    private final IManufacturerRepository manufacturerRepository;

    @Autowired
    public SuppliesController(ISuppliesRepository suppliesRepository, IManufacturerRepository manufacturerRepository) {
        this.suppliesRepository = suppliesRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/supplies")
    public String findAll(Model model) {
        List<Supplies> supplies = suppliesRepository.findAll();
        model.addAttribute("supplies", supplies); // attribute - "${supplies}"
        return "supplies/supplies-list";
    }
    @GetMapping("/supplies-create")
    public String createSuppliesForm(Supplies supplies, Model model) {
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        return "supplies/supplies-create";
    }

    @PostMapping("/supplies-create")
    public String createSupplies(@Valid Supplies supplies) {
        supplies.setSuppliesDate(LocalDate.now());
        suppliesRepository.save(supplies);
        return "redirect:/supplies/supplies-list";
    }

    @GetMapping("/supplies-delete/{id}")
    public String deleteSupplies(@PathVariable("id") Long id) {
        suppliesRepository.deleteById(id);
        return "redirect:/supplies/supplies-list";
    }

    @GetMapping("/supplies-update/{id}")
    public String updateSuppliesForm(@PathVariable("id") Long id, Model model) {
        Supplies supplies = suppliesRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid supplies ID" + id));
        model.addAttribute("supplies", supplies);
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        return "supplies/supplies-update";
    }

    @PostMapping("/supplies-update")
    public String updateSupplies(Supplies supplies) {
        suppliesRepository.save(supplies);
        return "redirect:/supplies/supplies-list";
    }
}
