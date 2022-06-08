package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.Category;
import com.example.devedbaseproject.models.ProductSubtype;
import com.example.devedbaseproject.models.ProductType;
import com.example.devedbaseproject.repository.ICategoryRepository;
import com.example.devedbaseproject.repository.IProductSubtypeRepository;
import com.example.devedbaseproject.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private final ICategoryRepository categoryRepository;
    private final IProductTypeRepository productTypeRepository;
    private final IProductSubtypeRepository productSubtypeRepository;

    @Autowired
    public CategoryController(ICategoryRepository categoryRepository, IProductTypeRepository productTypeRepository, IProductSubtypeRepository productSubtypeRepository) {
        this.categoryRepository = categoryRepository;
        this.productTypeRepository = productTypeRepository;
        this.productSubtypeRepository = productSubtypeRepository;
    }

    @GetMapping("/category")
        public String findAll(Model model){
        List<Category> category = categoryRepository.findAll();
        List<ProductType> productType = productTypeRepository.findAll();
        List<ProductSubtype> productSubtype = productSubtypeRepository.findAll();
        model.addAttribute("category", category); // attribute - "${products}"
        model.addAttribute("productType", productType);
        model.addAttribute("productSubtype", productSubtype);
        return "category/category-list";
    }
    @GetMapping("/category-create")
    public String createCategoryForm(Category category){
        return "category/category-create";
    }

    @GetMapping("/type-create")
    public String createTypeForm(ProductType productType, Model model) {
        List<ProductType> productTypeList = productTypeRepository.findAll();
        model.addAttribute("productTypeList", productTypeList);
        return "category/productType-create";
    }
    @GetMapping("/subtype-create")
    public String createSubtypeForm(ProductSubtype productSubtype, Model model) {
        List<ProductSubtype> productSubtypeList = productSubtypeRepository.findAll();
        model.addAttribute("productSubtypeList", productSubtypeList);
        return "category/productSubtype-create";
    }


    @PostMapping("/category-create")
    public String createCategory(Category category){
        categoryRepository.save(category);
        return "redirect:/category";
    }
    @PostMapping("/type-create")
    public String createType(ProductType productType){
        productTypeRepository.save(productType);
        return "redirect:/category";
    }
    @PostMapping("/subtype-create")
    public String createSubtype(ProductSubtype productSubtype){
        productSubtypeRepository.save(productSubtype);
        return "redirect:/category";
    }

    @GetMapping("/category-delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryRepository.deleteById(id);
        return "redirect:/category";
    }
    @GetMapping("/type-delete/{id}")
    public String deleteType(@PathVariable("id") Long id){
        productTypeRepository.deleteById(id);
        return "redirect:/category";
    }
    @GetMapping("/subtype-delete/{id}")
    public String deleteSubtype(@PathVariable("id") Long id) {
        productSubtypeRepository.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("/category-update/{id}")
    public String updateCategoryForm(@PathVariable("id") Long id, Model model){
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid category ID" + id));
        model.addAttribute("category", category);
        return "category/category-update";
    }

    @PostMapping("/category-update")
    public String updateCategory(Category category){
        categoryRepository.save(category);
        return "redirect:/category";
    }
}
