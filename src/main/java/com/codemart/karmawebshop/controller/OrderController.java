package com.codemart.karmawebshop.controller;

import com.codemart.karmawebshop.dto.Mapper;
import com.codemart.karmawebshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    Mapper mapper;

    @RequestMapping("/orders")
    public String order(Model model) {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        System.out.println(role);
        if (role.equals("ADMIN")) {
            model.addAttribute("orders", orderService.getAllOrders()
                    .stream()
                    .map(x -> mapper.orderToOrderDTO(x))
                    .collect(Collectors.toList()));
        } else {
            model.addAttribute("orders", orderService.getOrdersByUser(SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName())
                    .stream()
                    .map(x -> mapper.orderToOrderDTO(x))
                    .collect(Collectors.toList()));
        }
        return "orders";
    }

    @RequestMapping("orders/accept/{id}")
    public String acceptOrder(@PathVariable long id) {
        orderService.setOrderStatus(id, "ACCEPTED");
        return "redirect:/orders";
    }

    @RequestMapping("orders/decline/{id}")
    public String declineOrder(@PathVariable long id) {
        orderService.setOrderStatus(id, "DECLINED");
        return "redirect:/orders";
    }
}
