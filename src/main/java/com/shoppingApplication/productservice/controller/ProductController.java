package com.shoppingApplication.productservice.controller;

import com.shoppingApplication.productservice.dto.ProductRequest;
import com.shoppingApplication.productservice.dto.ProductResponse;
import com.shoppingApplication.productservice.repository.ProductRepository;
import com.shoppingApplication.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){

    }
}
