package com.codemart.karmawebshop.service.impl;

import com.codemart.karmawebshop.entity.*;
import com.codemart.karmawebshop.repository.OrderRepository;
import com.codemart.karmawebshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    ProductToCartService productToCartService;


    @Override
    @Transactional
    public Order placeOrder(Order order, String username) throws IOException, MessagingException {
        Cart cart = cartService.getUserActiveCart(username);
        order.setCart(cart);
        order.setStatus("SUBMITTED");
        order.setTotalPrice(cartService.getTotal(cart.getId()));
        order.setPlacedOn(LocalDateTime.now());
        orderRepository.save(order);
        cart.setActive(false);
        cartService.createCart(new Cart(userService.getUserByUsername(username),true));


        //send mail

        Mail mail = new Mail();
        mail.setFrom("rus.tudor42test@gmail.com");//replace with your desired email
        mail.setMailTo(order.getCart().getUser().getEmail());
        mail.setSubject("New order from Karma Shop!");

        Map<String, Object> model = new HashMap<String, Object>();

        List<ProductToCart> productToCartList = productToCartService.getAllProductsFromCart(order.getCart());

        model.put("name", order.getCart().getUser().getUsername());
        model.put("country", order.getDelivery().getAddress().getCountry());
        model.put("city",order.getDelivery().getAddress().getCity());
        model.put("street",order.getDelivery().getAddress().getStreet());
        model.put("number",order.getDelivery().getAddress().getNumber());
        model.put("phone",order.getDelivery().getTelephone());
        model.put("products", productToCartList);
        model.put("total", order.getTotalPrice());
        model.put("sign", "Karma Store");
        mail.setProps(model);

        emailSenderService.sendEmail(mail);

        return order;
    }

    @Override
    public List<Order> getOrdersByUser(String username) {
        return orderRepository.getAllByCartUserUsername(username);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void setOrderStatus(long id, String status) {
        orderRepository.getOne(id).setStatus(status);
    }
}
