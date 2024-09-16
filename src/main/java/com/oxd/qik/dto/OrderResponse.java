package com.oxd.qik.dto;

import com.oxd.qik.model.Order;

import java.util.List;

public record OrderResponse(Long id, Boolean discount_applied, Long total_amount, Long final_price, Long saving_amount, CustomerResponse customer, List<OrderProductResponse> products) {
    public OrderResponse(Order order) {
        this(
                order.getId(),
                order.getDiscountApplied(),
                order.getTotalAmount(),
                order.getFinalPrice(),
                order.getSavingAmount(),
                new CustomerResponse(order.getCustomer()),
                order.getProducts().stream().map(OrderProductResponse::new).toList()
        );
    }
}
