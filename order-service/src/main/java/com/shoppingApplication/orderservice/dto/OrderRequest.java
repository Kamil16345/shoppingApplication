package com.shoppingApplication.orderservice.dto;

import com.shoppingApplication.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
