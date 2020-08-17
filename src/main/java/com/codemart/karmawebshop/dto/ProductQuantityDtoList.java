package com.codemart.karmawebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductQuantityDtoList {
    private ArrayList<ProductQuantityDto> productQuantityDtoList;

    public ProductQuantityDtoList(ArrayList<ProductQuantityDto> productQuantityDtoList) {
        this.productQuantityDtoList = productQuantityDtoList;
    }

    public ProductQuantityDtoList() {

    }

    public List<ProductQuantityDto> getProductQuantityDtoList() {
        return productQuantityDtoList;
    }

    public void setProductQuantityDtoList(ArrayList<ProductQuantityDto> productQuantityDtoList) {
        this.productQuantityDtoList = productQuantityDtoList;
    }
}
