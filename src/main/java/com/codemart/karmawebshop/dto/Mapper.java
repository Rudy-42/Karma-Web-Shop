package com.codemart.karmawebshop.dto;

import com.codemart.karmawebshop.entity.Delivery;
import com.codemart.karmawebshop.entity.Order;
import com.codemart.karmawebshop.entity.ProductToCart;
import com.codemart.karmawebshop.service.CartService;
import com.codemart.karmawebshop.service.ProductService;
import com.codemart.karmawebshop.service.ProductToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductToCartService productToCartService;

    public ProductToCart productToCartDTOtoProductToCart(ProductToCartDTO productToCartDTO) {
        return new ProductToCart(productService.getProductById(productToCartDTO.getProductId()),
                cartService.getCartById(productToCartDTO.getCartId()),
                productToCartDTO.getQuantity());
    }

    public OrderDTO orderToOrderDTO(Order order) {
        return new OrderDTO((int)order.getId(),
                productToCartService.getAllProductsFromCart(order.getCart()),
                createAddressStringFromDelivery(order.getDelivery()),
                (int)order.getTotalPrice(),
                order.getStatus(),
                order.getPlacedOn());
    }

    private String createAddressStringFromDelivery(Delivery delivery) {
        return delivery.getAddress().getCountry() + ", " + delivery.getAddress().getCity() + ", Str: "
                + delivery.getAddress().getStreet() + ", Nr: " + delivery.getAddress().getNumber() + " - "
                + delivery.getTelephone();
    }
}
