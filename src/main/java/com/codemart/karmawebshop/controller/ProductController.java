package com.codemart.karmawebshop.controller;

import com.codemart.karmawebshop.entity.Product;
import com.codemart.karmawebshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("products", productService.getAllActive());
        model.addAttribute("product", new Product());
        model.addAttribute("category", productService.getCategoryAndSize());
        return "category";
    }

    @GetMapping("/category/{categoryFiltered}")
    public String filteredCategory(Model model, @PathVariable(required = false) String categoryFiltered){
        model.addAttribute("products", productService.getProductsByCategory(categoryFiltered));
        model.addAttribute("product", new Product());
        model.addAttribute("category", productService.getCategoryAndSize());
        return "category";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteItem(@PathVariable String id) {
        productService.deactivateProduct(Long.parseLong(id));
        return "redirect:/category";
    }

    @PostMapping("/upload/item")
    public String uploadItem(@RequestParam("file") MultipartFile file, @ModelAttribute(value = "product") Product product) {
        productService.save(file, product);
        return "redirect:/category";
    }
}
