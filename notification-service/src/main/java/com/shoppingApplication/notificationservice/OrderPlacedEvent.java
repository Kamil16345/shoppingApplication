package com.shoppingApplication.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
}
