package com.codemart.karmawebshop.dto;

import com.codemart.karmawebshop.entity.Delivery;
import com.codemart.karmawebshop.entity.Product;
import com.codemart.karmawebshop.entity.ProductToCart;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    int orderId;
    List<ProductToCart> products;
    String delivery;
    int total;
    String status;
    LocalDateTime time;

    public OrderDTO(int orderId, List<ProductToCart> products, String delivery, int total, String status, LocalDateTime time) {
        this.orderId = orderId;
        this.products = products;
        this.delivery = delivery;
        this.total = total;
        this.status = status;
        this.time = time;
    }

    public OrderDTO() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ProductToCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductToCart> products) {
        this.products = products;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", delivery=" + delivery +
                ", total=" + total +
                ", time=" + time +
                '}';
    }
}
