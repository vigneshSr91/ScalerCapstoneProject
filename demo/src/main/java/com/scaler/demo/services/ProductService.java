package com.scaler.demo.services;

import com.scaler.demo.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();
}
