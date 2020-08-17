package com.codemart.karmawebshop.controller;

import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.Order;
import com.codemart.karmawebshop.entity.ProductToCart;
import com.codemart.karmawebshop.service.CartService;
import com.codemart.karmawebshop.service.OrderService;
import com.codemart.karmawebshop.service.ProductToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;

    @Autowired
    ProductToCartService productToCartService;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cart userActiveCart = cartService.getUserActiveCart(authentication.getName());
        model.addAttribute("products", productToCartService.getAllProductsFromCart(userActiveCart));
        model.addAttribute("total", cartService.getTotal(userActiveCart.getId()));
        model.addAttribute("order", new Order());
        return "checkout";
    }

    @RequestMapping("/processOrder")
    public String processOrder(@ModelAttribute(value = "oder") Order order,Model model) throws IOException, MessagingException {
        Order o = orderService.placeOrder(order,SecurityContextHolder.getContext().getAuthentication().getName() );

        List<ProductToCart> productToCartList = productToCartService.getAllProductsFromCart(order.getCart());

        model.addAttribute("orderId", order.getId());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        model.addAttribute("date", formatter.format(date));
        model.addAttribute("total", order.getTotalPrice());
        model.addAttribute("address", order.getDelivery().getAddress());
        model.addAttribute("products", productToCartList);


        return "confirmation";
    }

    @RequestMapping("/confirmation")
    public String confirmation() {

        return "confirmation";
    }
}
