package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ProductController {

    private final IProductRepository productRepository;
    private final IProductParameterRepository productParameterRepository;
    private final IProductParameterValueRepository ppvalueRepository;
    private final IManufacturerRepository manufacturerRepository;
    private final IProductSubtypeRepository subtypeRepository;
    private final ITagRepository tagRepository;


    @Autowired
    public ProductController(IProductRepository productRepository, IProductParameterRepository productParameterRepository,
                             IProductParameterValueRepository ppvalueRepository, IManufacturerRepository manufacturerRepository,
                             IProductSubtypeRepository subtypeRepository, ITagRepository tagRepository) {
        this.productRepository = productRepository;
        this.productParameterRepository = productParameterRepository;
        this.ppvalueRepository = ppvalueRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.subtypeRepository = subtypeRepository;
        this.tagRepository = tagRepository;
    }


    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> product = productRepository.findAll();
        model.addAttribute("products", product); // attribute - "${products}"
//        return "product-list";
        return "FRONT/7";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product, Model model) {
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());
        return "product-create";
    }


    @PostMapping("/product-create")
    public String createProduct(@Valid Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());
        return "product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{productId}")
    public String findProductById(@PathVariable("productId") Long productId,
                                  Model model) {
        List<ProductParameter> parameters = productParameterRepository.findAll();
        model.addAttribute("parameters", parameters);

        ProductParameter newPP = new ProductParameter("");

        PPValueWrapper wrapper = new PPValueWrapper();
        ProductParameterValue ppv = new ProductParameterValue();
        ppv.setParameter(newPP);
        ppv.setIntValue(0);
        ppv.setStringValue("test");
        wrapper.getPpValueList().add(ppv);
        model.addAttribute("wrapper", wrapper);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Error Found");
        }
        return "product";
    }

    @PostMapping("/products/{productId}")
    public String setParameters(@RequestParam Map<String, String> form,
                                @ModelAttribute PPValueWrapper wrapper, Model model,
                                @PathVariable("productId") Long productId) {
        System.out.println(wrapper.getPpValueList() != null ? wrapper.getPpValueList().size() : "null list");
        System.out.println("--");

        model.addAttribute("wrapper", wrapper);

        System.out.println(form);
        System.out.println(wrapper);
        System.out.println(wrapper.getPpValueList());
        System.out.println(wrapper.getPpValueList().toString());
        System.out.println(wrapper.getPpValueList().get(0).toString());
        System.out.println(wrapper.getPpValueList().size());
        System.out.println(form.keySet());

        Optional<Product> tempproduct = productRepository.findById(productId);

        Product product = new Product();
        if (tempproduct.isPresent()) {
            product = tempproduct.get();
        } else {
            System.out.println("Error Found, likely no product");
        }


        for (ProductParameterValue ppvalue: wrapper.getPpValueList()) {
            ppvalue.setProduct(product);

            Optional<ProductParameter> tempproductparameter = productParameterRepository.findByName(ppvalue.getParameter().getName());

            ProductParameter productparameter = new ProductParameter();
            if (tempproductparameter.isPresent()) {
                productparameter = tempproductparameter.get();
            } else {
                System.out.println("Error Found, likely no product");
            }

//            Optional<ProductParameter> parameter = productParameterRepository.findByName(ppvalue.getParameter().getName());
//            if (parameter.isPresent()) {
//                ppvalue.setParameter(parameter.get());
//            } else {
//                System.out.println("Error Found, likely no parameter");
//            }
            ppvalue.setParameter(productparameter);
            ppvalueRepository.save(ppvalue);
            product.getParameterValues().add(ppvalue);
            productRepository.save(product);
            System.out.println(ppvalue);
            System.out.println(ppvalue.getParameter());
            System.out.println(ppvalue.getParameter().getName());
        }

        return "redirect:/products/" + productId;
    }




    @GetMapping("/products/{productId}/settags")
    public String setTagsForm(@PathVariable("productId") Long productId,
                                  Model model) {
        List<Tag> tags = tagRepository.findAll();

        model.addAttribute("tags", tags);

        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag());
        model.addAttribute("tagList", tagList);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Error Found");
        }
        return "setTags";
    }

    @PostMapping("/products/{productId}/settags")
    public String saveTagList(@PathVariable("productId") Long productId,
                              Model model,
                              @ModelAttribute List<Tag> tagList,
                              @RequestParam Map<String, String> form) {

        System.out.println(tagList);
        System.out.println(form);
        Optional<Product> tempproduct = productRepository.findById(productId);

        Product product = new Product();
        if (tempproduct.isPresent()) {
            product = tempproduct.get();
        } else {
            System.out.println("Error Found, likely no product");
        }

        if (tagList == null) {
            System.out.println("Nullerror");
        }
        else {
            for (Tag tag: tagList) {
                product.getTags().add(tag);
            }
        }

        return "redirect:/products/" + productId;
    }


}
