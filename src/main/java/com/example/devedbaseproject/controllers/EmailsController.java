package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.logic.ProductRecommendation;
import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.ICustomerRepository;
import com.example.devedbaseproject.repository.IEmailRepository;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IProductRepository;
import com.example.devedbaseproject.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/emails")
public class EmailsController {
    private final IEmailRepository repository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    private ProductRecommendation productRecommendation;

    List<Customer> customerSendingList;

    @Autowired
    private EmailSender sender;

    @Autowired
    public EmailsController(IEmailRepository repository, ICustomerRepository customerRepository, IProductRepository productRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.customerSendingList = new ArrayList<>();
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Email> emailList = repository.findAll();
        Collections.sort(emailList, new Comparator<Email>(){
            public int compare(Email o1, Email o2)
            {
                return o1.getId().compareTo(o2.getId());
            }
        });

        model.addAttribute("emails", emailList);
        return "emails/history-email";
    }

    @GetMapping("/customer/{customerId}")
    public String findEmailByCustomerID(@PathVariable("customerId") Long customerId, Model model) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID" + customerId));
        List<Email> emailList = new ArrayList<>();

        for (Email e: repository.findAll()) {
            if (e.getCustomers().contains(customer)) {
                emailList.add(e);
            }
            }

        model.addAttribute("emails", emailList);
        return "emails/history-email";
    }

    @GetMapping("/new")
    public String createNewEmail(@ModelAttribute("email") Email email, Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "emails/new";
    }

    @PostMapping()
    public String createEmailForOneProduct(@AuthenticationPrincipal Employee employee,
            @ModelAttribute("email") Email email) {
        Product product = productRepository.findById(email.getProduct().getId()).orElseThrow();
        Email newemail = new Email(product);
        this.productRecommendation = new ProductRecommendation(product,
                customerRepository.findAll(), repository);
        List<Customer> cList = productRecommendation.getCustomerSendingList();
        if (cList.isEmpty()) {
            System.out.println("Предложить продукт некому");
            return "redirect:/emails";
        }
        newemail.setCustomers(cList);
        newemail.setEmployee(employee);
        newemail.setSend(false);

        repository.save(newemail);
        this.customerSendingList = cList;
        return "redirect:/emails";
    }

    @PostMapping("/newEmails")
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
        return "emails/history-email";
    }

    @GetMapping("/{id}/send")
    public String sendEmail(@PathVariable("id") Long id, Model model) {
        Optional<Email> email = repository.findById(id);
        sendEmail(email.get().getProduct());
        String servicemessage = "Email with id " + email.get().getId() +
        " for customers:  " + email.get().getCustomers() +
        " about product: " + email.get().getProduct() +
        " has been sent.";
        System.out.println(servicemessage);
        email.get().setSend(true);
        repository.save(email.get());
        model.addAttribute("servicemessage", servicemessage);
        return "emails/emailSent";
    }

    public void sendEmail(Product currentProduct) {
            for (Customer c : this.customerSendingList) {
                    String message = String.format("Добрый день, %s! \n" +
                                    "Рекомендуем приобрести следующий продукт: \n" +
                                    "%s",
                            c.getName(), currentProduct);
                    sender.send(c.getEmail(), "Рекомендуемый продукт", message);
            }
        System.out.println(customerSendingList);
        }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Email> email = repository.findById(id);
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
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