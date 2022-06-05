package com.example.devedbaseproject.controllers;



import com.example.devedbaseproject.models.Supplies;
import com.example.devedbaseproject.repository.SuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SuppliesController {
    private final SuppliesRepository suppliesRepository;

    @Autowired
    public SuppliesController(SuppliesRepository suppliesRepository){

        this.suppliesRepository = suppliesRepository;
    }


    @GetMapping("/supplies")
    public String findAll(Model model ){
        List<Supplies> supplies = suppliesRepository.findAll();
        model.addAttribute("supplies", supplies); // attribute - "${supplies}"
        return "supplies-list";
    }
    @GetMapping("/supplies-create")
    public String createSuppliesForm(Supplies supplies){
        return "supplies-create";
    }


    @PostMapping("/supplies-create")
    public String createSupplies(Supplies supplies){
        suppliesRepository.save(supplies);
        return "redirect:/supplies";
    }

    @GetMapping("/supplies-delete/{id}")
    public String deleteSupplies(@PathVariable("id") Long id){
        suppliesRepository.deleteById(id);
        return "redirect:/supplies";
    }

    @GetMapping("/supplies-update/{id}")
    public String updateSuppliesForm(@PathVariable("id") Long id, Model model){
        Supplies supplies = suppliesRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid supplies ID" + id));
        model.addAttribute("supplies", supplies);
        return "supplies-update";
    }

    @PostMapping("/supplies-update")
    public String updateSupplies(Supplies supplies){
        suppliesRepository.save(supplies);
        return "redirect:/supplies";
    }
}
