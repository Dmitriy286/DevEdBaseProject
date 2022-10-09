package com.example.devedbaseproject.logic;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Email;
import com.example.devedbaseproject.models.Product;
import com.example.devedbaseproject.models.Tag;
import com.example.devedbaseproject.repository.IEmailRepository;
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
    private List<Customer> customerList;
    Product currentProduct;
    private List<Tag> productTagList;
    private List<Customer> customerSendingList;
    private IEmailRepository emailRepository;

    @Autowired
    private EmailSender sender;

    public ProductRecommendation() {
    }

    public ProductRecommendation(Product currentProduct, List<Customer> customerList,
                                 IEmailRepository emailRepository) {
        this.currentProduct = currentProduct;
        this.customerList = customerList;
        this.emailRepository = emailRepository;
        this.setCustomerSendingList();
    }

    //todo необходима доработка метода (зависит от реализации рассылки)
    public void setCustomerSendingList() {
        setProductTagList(this.currentProduct.getTags());
        this.customerSendingList = tagCompare();
        }

    public void sendEmail() {
        for (Customer c : this.customerSendingList) {
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
        List<Customer> listForSending = new ArrayList<>();
        for (Customer c : this.customerList) {
            for (Tag tag: tagList) {
                if (c.getTagList().contains(tag)) {
                    flag = true;
                }
                else{
                    flag = false;
                    break;
                }
            }
            if (flag && checkPreviousSending(c, currentProduct)) {
                listForSending.add(c);
            }
        }
        return listForSending;
    }

    public boolean checkPreviousSending(Customer c, Product p) {
            for (Email e: emailRepository.findAll()) {
                if (e.getCustomers().contains(c)) {
                    if (e.getDate().plusDays(30).isAfter(LocalDate.now())) {
                        return false;
                    }
                }
            }
            for (Email e: emailRepository.findByProduct(p)) {
                if (e.getCustomers().contains(c)) {
                        return false;
                    }
            }
        return true;
    }

    public void setProductTagList(List<Tag> productTagList) {
        this.productTagList = productTagList;
    }

    public List<Tag> getProductTagList() {
        return this.productTagList;
    }

    public List<Customer> getCustomerSendingList() {
        return customerSendingList;
    }
}
