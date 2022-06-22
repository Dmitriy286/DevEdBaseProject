package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import com.example.devedbaseproject.models.Tag;
import com.example.devedbaseproject.repository.IRoleRepository;
import com.example.devedbaseproject.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tags")
public class TagsController {
    private final ITagRepository repository;

    @Autowired
    public TagsController(ITagRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Tag> tagList = repository.findAll();

        Collections.sort(tagList, new Comparator<Tag>(){
            public int compare(Tag o1, Tag o2)
            {
                return o1.getId().compareTo(o2.getId());
            }
        });

        model.addAttribute("tags", tagList);
        return "tag/showAll";
    }

    @PostMapping("/filter")
    public String findTagByName(@RequestParam("filter") String filter, Model model) {
        Iterable<Tag> tags;
        if (filter != null && !filter.isEmpty()) {
            tags = repository.findByName(filter);
        }
        else {
            tags = repository.findAll();
        }
        model.addAttribute("tags", tags);
        return "tag/showAll";
    }

    @GetMapping("/new")
    public String newTag(@ModelAttribute("tag") Tag tag) {
        return "tag/new";
    }

    @PostMapping()
    public String createTag(@ModelAttribute("tag") Tag tag) {
        repository.save(tag);
        return "redirect:/tags";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Tag> tag = repository.findById(id);
        if (tag.isPresent()) {
            model.addAttribute("tag", tag.get());
            System.out.println(tag);
        }
        else {
            System.out.println("Error Found");
        }

        return "tag/edit";
    }

//    @PatchMapping("/{id}")
    @PostMapping("/{id}")
    public String update(@ModelAttribute("tag") Tag tag, @PathVariable("id") Long id) {
        repository.save(tag);

        return "redirect:/tags";
    }

    //    @DeleteMapping("/{id}/delete")
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/tags";
    }


}