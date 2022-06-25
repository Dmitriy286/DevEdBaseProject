package com.example.devedbaseproject.logic;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Email;
import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.Tag;
import com.example.devedbaseproject.repository.ICustomerRepository;
import com.example.devedbaseproject.repository.IEmailRepository;
import com.example.devedbaseproject.repository.IProductRepository;
import com.example.devedbaseproject.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*продукт -> тэги продукта

    пробегаемся по тэгам всех клиентов

    если все тэги продукта совпадают с тэгами клиента
    и последняя отправка емэйла клиенту была больше месяца назад ->

    добавляем клиента в список рассылки по текущему продукту

    на выходе список клиентов и айди продукта, которые передаются в контроллер рассылки сообщений
    send -> письмо уходит клиентам

     */

public class ProductRecommendation {
    private final IProductRepository productRepository;
    private final ICustomerRepository customerRepository;

    private final IEmailRepository emailRepository;
    private List<Tag> productTagList;
    private List<Customer> customerSendingList;

    @Autowired
    private EmailSender sender;

    public ProductRecommendation(IProductRepository productRepository, ICustomerRepository customerRepository, IEmailRepository emailRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.emailRepository = emailRepository;

    }

    //todo необходима доработка метода (зависит от реализации рассылки)
    public List<Customer> createDataForEmail(Product currentProduct) {
        this.setProductTagList(currentProduct);
        return this.tagCompare();
        }

    public void sendEmail(Product currentProduct) {
        for (Customer c : this.createDataForEmail(currentProduct)) {
            if (!StringUtils.isEmpty(c.getEmail())) {
                String message = String.format("Добрый день, %s! \n" +
                                "Рекомендуем приобрести следующий продукт: \n" +
                                "%s",
                        c.getName(), currentProduct);
                sender.send(c.getEmail(), "Рекомендуемый продукт", message);
            }
        }
    }

    public List<Customer> tagCompare() {
        boolean flag = true;
        List<Tag> tagList = getProductTagList();
        List<Customer> customerList = getCustomerList();

        for (Customer c : customerList) {
            for (Tag tag: tagList) {
                if (!c.getTagCountMap().containsKey(tag)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                this.customerSendingList.add(c);
            }
        }

        return this.customerSendingList;

    }

    public void checkPreviousSending() {
        this.tagCompare();
        for (Customer c: this.customerSendingList) {
            for (Email e: emailRepository.findByCustomer(c)) {
                if (e.getDate().plusDays(30).isAfter(LocalDate.now())) {
                    this.customerSendingList.remove(c);
                }
            }
        }
    }

    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    public void setProductTagList(Product currentProduct) {
        this.productTagList = currentProduct.getTags();
    }

    public List<Tag> getProductTagList() {
        return this.productTagList;
    }


}
