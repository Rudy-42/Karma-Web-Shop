package com.codemart.karmawebshop.controller;

import com.codemart.karmawebshop.dto.Mapper;
import com.codemart.karmawebshop.dto.ProductQuantityDto;
import com.codemart.karmawebshop.dto.ProductQuantityDtoList;
import com.codemart.karmawebshop.dto.ProductToCartDTO;
import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.Product;
import com.codemart.karmawebshop.entity.ProductToCart;
import com.codemart.karmawebshop.entity.User;
import com.codemart.karmawebshop.service.CartService;
import com.codemart.karmawebshop.service.ProductToCartService;
import com.codemart.karmawebshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    Mapper mapper;
    @Autowired
    ProductToCartService productToCartService;

    @RequestMapping("/addToCart/productId/{productId}/quantity/{quantity}")
    public String addToCart(@PathVariable int productId,@PathVariable int quantity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartService.getUserActiveCart(authentication.getName());
        productToCartService.save(mapper.productToCartDTOtoProductToCart(new ProductToCartDTO(productId,(int)cart.getId(),quantity)));
        return "redirect:/category";
    }

    @GetMapping("/cart")
    public String cart(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Cart cart = cartService.getUserActiveCart(currentPrincipalName);
        List<ProductToCart> productToCarts = productToCartService.getAllProductsFromCart(cart);
        List<ProductQuantityDto> products = productToCarts.stream().map(x->new ProductQuantityDto(x.getProduct(),x.getQuantity())).collect(Collectors.toList());
        ProductQuantityDtoList p = new ProductQuantityDtoList();
        p.setProductQuantityDtoList(new ArrayList<ProductQuantityDto>(10));
        p.getProductQuantityDtoList().addAll(products);
        model.addAttribute("products",p);
        int total = 0;
        for (ProductQuantityDto p1 : p.getProductQuantityDtoList()){
            total+=p1.getQuantity()*p1.getPrice();
        }

        model.addAttribute("total", total);

        return "cart";
    }

    @RequestMapping("/updateCart")
    public String updateCart(@ModelAttribute ProductQuantityDtoList products){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Cart cart = cartService.getUserActiveCart(currentPrincipalName);

        productToCartService.update(products.getProductQuantityDtoList(),cart);



        return "redirect:/cart";
    }
}
