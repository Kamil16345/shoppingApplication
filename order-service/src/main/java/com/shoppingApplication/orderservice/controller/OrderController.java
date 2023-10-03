package com.shoppingApplication.orderservice.controller;

import com.shoppingApplication.orderservice.dto.OrderRequest;
import com.shoppingApplication.orderservice.model.Order;
import com.shoppingApplication.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")
    @Retry(name="inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
    }
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Something went wrong, please contact your administrator");
    }
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }
}
