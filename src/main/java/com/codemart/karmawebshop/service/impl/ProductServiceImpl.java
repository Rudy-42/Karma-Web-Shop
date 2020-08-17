package com.codemart.karmawebshop.service.impl;

import com.codemart.karmawebshop.dto.CategoryAndSize;
import com.codemart.karmawebshop.entity.Product;
import com.codemart.karmawebshop.repository.ProductRepository;
import com.codemart.karmawebshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(MultipartFile image, Product product) {
        product.setImgPath(saveImage(image));
        product.setActive(true);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllActive() {
        return productRepository.getAllByActiveTrue();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.getOne(id);
    }

    @Override
    @Transactional
    public void deactivateProduct(long id) {
        Product productToBeDeactivated = productRepository.getOne(id);
        productToBeDeactivated.setActive(false);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getAllByCategoryAndActiveTrue(category);
    }

    @Override
    public List<Product> getProductsByPriceRange(long p1, long p2) {
        return productRepository.getAllByPriceBetween(p1, p2);
    }

    @Override
    public List<String> getProductsCategory() {
        return productRepository.findAll().stream().map(Product::getCategory).distinct().collect(Collectors.toList());
    }

    @Override
    public List<CategoryAndSize> getCategoryAndSize() {
        return getProductsCategory().stream().map(c -> new CategoryAndSize(c, productRepository.countProductByCategoryAndActiveTrue(c))).collect(Collectors.toList());
    }


    private String saveImage(MultipartFile image) {
        String path = System.getProperty("user.home") + "\\karma_storage\\products\\" + image.getOriginalFilename();
        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image.getOriginalFilename();
    }
}
