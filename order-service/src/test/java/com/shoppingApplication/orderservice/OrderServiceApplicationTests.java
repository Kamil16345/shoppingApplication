package com.shoppingApplication.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingApplication.orderservice.dto.OrderLineItemsDto;
import com.shoppingApplication.orderservice.dto.OrderRequest;
import com.shoppingApplication.orderservice.model.OrderLineItems;
import com.shoppingApplication.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.MySQLContainer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderServiceApplicationTests {
	@Container
	static MySQLContainer MY_SQL_CONTAINER = new MySQLContainer(DockerImageName.parse("mysql").withTag("5.7.34"));

	@DynamicPropertySource
	static void mysqlProperties(DynamicPropertyRegistry registry) {
		MY_SQL_CONTAINER.start();
		registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
		registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
		registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
	}

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private OrderRepository orderRepository;

	@MockBean
	OrderLineItemsDto orderLineItemsDto;
	@Test
	public void shouldPlaceOrder() throws Exception {
		OrderRequest orderLineItemsDto = getOrderRequest();
		String orderRequestString = objectMapper.writeValueAsString(orderLineItemsDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(orderRequestString))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	@Test
	public void shouldGetAllOrders() throws Exception {
		OrderRequest orderRequest = getOrderRequest();
		String orderRequestString = objectMapper.writeValueAsString(orderRequest);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(orderRequestString))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	public OrderRequest getOrderRequest(){
		OrderLineItemsDto orderLineItems = new OrderLineItemsDto("123-123", BigDecimal.valueOf(22), 3);
		List<OrderLineItemsDto> orderLineItemsDtoList = new ArrayList<>();
		orderLineItemsDtoList.add(orderLineItems);
		return OrderRequest.builder()
				.orderLineItemsDtoList(orderLineItemsDtoList)
				.build();
	}
}
