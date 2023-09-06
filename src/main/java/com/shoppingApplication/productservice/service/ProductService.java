package com.shoppingApplication.productservice.service;

import com.shoppingApplication.productservice.dto.ProductRequest;
import com.shoppingApplication.productservice.dto.ProductResponse;
import com.shoppingApplication.productservice.model.Product;
import com.shoppingApplication.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product is saved: " + product.getId());
    }
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        products.stream().map(product -> mapToProductResponse());
        return productList;
    }

    private Object mapToProductResponse() {
        return null;
    }

}
