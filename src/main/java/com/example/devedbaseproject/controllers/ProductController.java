package com.example.devedbaseproject.controllers;


import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final ICategoryRepository categoryRepository;
    private final IProductTypeRepository typeRepository;
    private final IProductSubtypeRepository subtypeRepository;
    private final ITagRepository tagRepository;
    private final ICustomerRepository customerRepository;


    @Autowired
    public ProductController(IProductRepository productRepository, IProductParameterRepository productParameterRepository,
                             IProductParameterValueRepository ppvalueRepository, IManufacturerRepository manufacturerRepository,
                             ICategoryRepository categoryRepository, IProductTypeRepository typeRepository,
                             IProductSubtypeRepository subtypeRepository, ITagRepository tagRepository, ICustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.productParameterRepository = productParameterRepository;
        this.ppvalueRepository = ppvalueRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
        this.subtypeRepository = subtypeRepository;
        this.tagRepository = tagRepository;
        this.customerRepository = customerRepository;
    }


    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        List<ProductType> types = typeRepository.findAll();
        model.addAttribute("type", types);
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());

        return "product/products";
    }

    @PostMapping("/products")
    public String createProduct(@Valid Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product/{id}")
    public String cardProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
        model.addAttribute("value", ppvalueRepository.findAll());
        model.addAttribute("parameters", productParameterRepository.findAll());
        return "product/card-product";
    }

    @GetMapping("/employees/product/update/{id}")
    public String updateFormProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product ID" + id));
        model.addAttribute("product", product);
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        model.addAttribute("subtype", subtypeRepository.findAll());
        return "product/product-update";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product) {
        productRepository.save(product);
        return "redirect:/product/" + id;
    }

    @GetMapping("/product/parameters/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
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
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Error Found");
        }
        return "product/product-parameters";
    }

    @PostMapping("product/parameters/{id}")
    public String setParameters(@RequestParam Map<String, String> form,
                                @ModelAttribute PPValueWrapper wrapper, Model model,
                                @PathVariable("id") Long id) {
        System.out.println(wrapper.getPpValueList() != null ? wrapper.getPpValueList().size() : "null list");
        System.out.println("--");
        model.addAttribute("wrapper", wrapper);

        Optional<Product> tempproduct = productRepository.findById(id);

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
            ppvalue.setParameter(productparameter);
            ppvalueRepository.save(ppvalue);
            product.getParameterValues().add(ppvalue);
            productRepository.save(product);
        }
        return "redirect:/product/parameters/" + id;
    }

    @GetMapping("/products/{productId}/settags")
    public String setTagsForm(@PathVariable("productId") Long productId,
                                  Model model) {
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("tags", tags);

        TagWrapper wrapper = new TagWrapper();
        Tag t = new Tag();
        wrapper.getTagList().add(t);
        model.addAttribute("wrapper", wrapper);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Error Found");
        }
        return "product/setTags";
    }

    @PostMapping("/products/{productId}/settags")
    public String saveTagList(@PathVariable("productId") Long productId,
                              @ModelAttribute TagWrapper wrapper, Model model,
                              @RequestParam Map<String, String> form) {
        Optional<Product> tempproduct = productRepository.findById(productId);

        Product product = new Product();
        if (tempproduct.isPresent()) {
            product = tempproduct.get();
        } else {
            System.out.println("Error Found, likely no product");
        }

        if (wrapper.getTagList() == null) {
            System.out.println("Nullerror");
        }
        else {
            for (Tag tempTag: wrapper.getTagList()) {
                Optional<Tag> tag = tagRepository.findById(tempTag.getId());
                if (tag.isPresent()) {
                    product.getTags().add(tag.get());
                } else {
                    System.out.println("Error Found, likely no tag");
                }
            }
            productRepository.save(product);
            }
        return "redirect:/products/" + productId + "/settags";
    }
    @PostMapping("/products/filter")
    public String filter(@RequestParam("filter") String filter, Model model, @AuthenticationPrincipal Employee employeeAccount) {
        Iterable<Product> products;
        if (filter != null && !filter.isEmpty()) {
            products = productRepository.findByProductName(filter);
        }
        else {
            products = productRepository.findAll();
        }
        List<Customer> customers = customerRepository.findAll();

        model.addAttribute("employeeAccount", employeeAccount);
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);
        return "product/employees-account";
    }
}
