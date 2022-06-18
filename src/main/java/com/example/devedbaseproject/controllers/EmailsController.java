package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.ICustomerRepository;
import com.example.devedbaseproject.repository.IEmailRepository;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/emails")
public class EmailsController {
    private final IEmailRepository repository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    private final IEmployeeRepository employeeRepository;

    @Autowired
    public EmailsController(IEmailRepository repository, ICustomerRepository customerRepository, IProductRepository productRepository, IEmployeeRepository employeeRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Email> emailList = repository.findAll();
        model.addAttribute("emails", emailList);
        return "emails/showAll";
    }

    @GetMapping("/customer/{customerId}")
    public String findEmailByCustomerID(@PathVariable("customerId") Long customerId, Model model) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID" + customerId));
        List<Email> emailList = repository.findByCustomer(customer);
        model.addAttribute("emails", emailList);

        return "emails/showAll";
    }

    @GetMapping("/new")
    public String createNewEmail(@ModelAttribute("email") Email email) {
        return "emails/new";
    }

    @PostMapping()
    public String createEmail(@AuthenticationPrincipal Employee employee,
            @ModelAttribute("email") Email email) {

        Customer customer = customerRepository.findById(1L).orElseThrow();
        // этот клиент должен приходить из стэка или очереди предложки,
        // может быть отдельной сущности с таблице в БД "Предложения по клиентам"
        Product product = productRepository.findById(1L).orElseThrow(); //исправить на ввод айдишника
        // аналогично по продукту
        Email newemail = new Email(email.getDate(), email.getMessage());

        newemail.getProducts().add(product);
        newemail.setCustomer(customer);
        newemail.setEmployee(employee);
        newemail.setSend(false);

        repository.save(newemail);

        return "redirect:/emails";
    }

    @PostMapping("/newemails")
    public String sendEmailFilter(@RequestParam("sent") boolean sent, Model model) {

        List<Email> emailList = repository.findAll();
        List<Email> emailListToSend = new ArrayList<>();
        List<Email> sentEmails = new ArrayList<>();
        for (Email e : emailList) {
            if (!e.isSend()) {
                emailListToSend.add(e);
            }
            else {
                sentEmails.add(e);
            }
        }

        if (!sent) {
            model.addAttribute("emails", emailListToSend);
        }
        else {
            model.addAttribute("emails", sentEmails);
        }
        return "emails/showAll";
    }

    @GetMapping("/{id}/send")
    public String sendEmail(@PathVariable("id") Long id, Model model) {
        Optional<Email> email = repository.findById(id);
        String servicemessage = "Email with id " + email.get().getId() +
        "for customer with id " + email.get().getCustomer().getId() +
        "is sending to the e-mail adress: " + email.get().getCustomer().getEmail();
        System.out.println(servicemessage);
        email.get().setSend(true);
        repository.save(email.get());
        model.addAttribute("servicemessage", servicemessage);
        return "emails/emailSent";

    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Email> email = repository.findById(id);
        if (email.isPresent() && !email.get().isSend()) {
            String servicemessage = "Change email content:";
            System.out.println(servicemessage);
            model.addAttribute("servicemessage", servicemessage);
            model.addAttribute("email", email.get());
            System.out.println(email);
        }
        else if (email.isPresent() && email.get().isSend()) {
            String servicemessage = "Email has been already sent";
            System.out.println(servicemessage);
            model.addAttribute("servicemessage", servicemessage);
        }
        else {
            String servicemessage = "Error Found";
            System.out.println(servicemessage);
            model.addAttribute("servicemessage", servicemessage);
            return "emails/error";
        }

        return "emails/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("email") Email email, @PathVariable("id") Long id) {
        repository.save(email);

        return "redirect:/emails";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {
        Optional<Email> email = repository.findById(id);
        if (email.isPresent() && !email.get().isSend()) {
            repository.deleteById(id);
            System.out.println("Deleted");
        }
        else if (email.isPresent() && email.get().isSend())
        {
            System.out.println("Email has been already sent");
        }
        else {
            String servicemessage = "Error Found";
            System.out.println(servicemessage);
            model.addAttribute("servicemessage", servicemessage);
            return "emails/error";
        }

        return "redirect:/emails";
    }


}