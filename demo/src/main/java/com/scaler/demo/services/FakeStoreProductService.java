package com.scaler.demo.services;

import com.scaler.demo.dtos.FakeStoreProductDto;
import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDesc());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        Category category = new Category();
        //category.setId(dto.getId());
        category.setDesc(dto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {

        // call fakestore API
        FakeStoreProductDto fakeStoreProductDto =
            restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if( fakeStoreProductDto == null ){
            // or throw exception
            return null;
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
