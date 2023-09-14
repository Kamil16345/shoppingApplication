package com.shoppingApplication.inventoryservice.repository;

import com.shoppingApplication.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
