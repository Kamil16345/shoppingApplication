package com.shoppingApplication.orderservice.repository;

import com.shoppingApplication.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
