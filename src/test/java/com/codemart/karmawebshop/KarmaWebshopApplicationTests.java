package com.codemart.karmawebshop;

import com.codemart.karmawebshop.dto.Mapper;
import com.codemart.karmawebshop.dto.ProductToCartDTO;
import com.codemart.karmawebshop.entity.*;
import com.codemart.karmawebshop.repository.CartRepository;
import com.codemart.karmawebshop.repository.OrderRepository;
import com.codemart.karmawebshop.repository.ProductRepository;
import com.codemart.karmawebshop.repository.ProductToCartRepository;
import com.codemart.karmawebshop.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class KarmaWebshopApplicationTests {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductToCartRepository productToCartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    Mapper mapper;
    @Autowired
    CartService cartService;
    @Autowired
    ProductToCartService productToCartService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        Product product = new Product();
        product.setName("Red Sneaker");
        product.setCategory("Outdoor");
        product.setPrice(14);

        productRepository.save(product);
    }

    @Test
    @Transactional
    void testCart() {
        Cart cart = new Cart();
        cart.setActive(false);
//        User user = new User();
//        user.setUsername("Renato");
//        user.setPassword("oparola");
//        user.setRole("rol");
        cart.setUser(userService.getUserById(1L));
        cartRepository.save(cart);
    }

    @Test
    void testProductToCart() {
        ProductToCart productToCart1 = new ProductToCart(productRepository.getOne(1L),cartRepository.getOne(2L),2);
        ProductToCart productToCart2 = new ProductToCart(productRepository.getOne(2L),cartRepository.getOne(2L),3);

        productToCartRepository.saveAll(List.of(productToCart1,productToCart2));
    }

    @Test
    void testOrder() {
        Order order = new Order();
        order.setCart(cartRepository.getOne(2L));
        UserAddress userAddress = new UserAddress();
        userAddress.setCity("Cluj");
        userAddress.setCountry("RO");
        userAddress.setNumber("24/A");
        userAddress.setStreet("Petrofil Sandor");
        Delivery delivery = new Delivery();
        delivery.setAddress(userAddress);
        delivery.setTelephone("0743123121");
        order.setDelivery(delivery);
        order.setPlacedOn(LocalDateTime.now().plusMinutes(1));
        order.setStatus("PLACED");
        order.setTotalPrice(20);
        orderRepository.save(order);
    }

    @Test
    void test() {
        String path = System.getProperty("user.home");
        System.out.println(path);
    }

    @Test
    @Transactional
    void testActiveCart() {
        System.out.println(cartRepository.getByActiveTrueAndUserId(1L));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setRole("USER");
        user.setEmail("email@mail.ro");
        userService.addUser(user);
    }

    @Test
    void testGetProductsByCategory() {
//        System.out.println(productService.getAll());
        System.out.println(productService.getProductsByCategory("Dulceata"));
        System.out.println(productService.getProductsByCategory("Cat"));
        System.out.println(productService.getProductsCategory());
    }

    @Test
    @Transactional
    void testGetCartTotal() {
        System.out.println(cartService.getTotal(2L));
    }

    @Test
    @Transactional
    void testGetUserOrders() {
//        System.out.println(orderService.getOrdersByUser("userr"));
        System.out.println(orderService.getAllOrders());
    }


}
