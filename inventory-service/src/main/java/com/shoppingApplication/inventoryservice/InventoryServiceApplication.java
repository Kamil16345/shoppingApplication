package com.shoppingApplication.inventoryservice;

import com.shoppingApplication.inventoryservice.model.Inventory;
import com.shoppingApplication.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Samsung");
			inventory.setQuantity(123);

			Inventory inventory2 = new Inventory();
			inventory.setSkuCode("Nokia");
			inventory.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
		};
	}

}
