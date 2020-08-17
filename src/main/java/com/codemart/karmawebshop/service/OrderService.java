package com.codemart.karmawebshop.service;

import com.codemart.karmawebshop.entity.Order;

import javax.mail.MessagingException;
import java.io.IOException;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order, String username) throws IOException, MessagingException;
    List<Order> getOrdersByUser(String username);
    List<Order> getAllOrders();
    void setOrderStatus(long id, String status);

}
