package com.oxd.qik.dto;

import com.oxd.qik.model.OrderProduct;

public record OrderProductResponse(
        String product_id,
        Integer quantity,
        Long price,
        Long total_price,
        Long final_price,
        Long saving_amount
) {

    public OrderProductResponse(OrderProduct orderProduct) {
        this(orderProduct.getProductId(), orderProduct.getQuantity(), orderProduct.getPrice(), orderProduct.getTotalPrice(), orderProduct.getFinalPrice(), orderProduct.getSavingAmount());
    }
}
