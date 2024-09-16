package com.oxd.qik.service.discounts.impl;

import com.oxd.qik.dto.PromotionResponse;
import com.oxd.qik.model.OrderProduct;
import com.oxd.qik.service.discounts.CalculateDiscount;

public class CalculateFlatPercent implements CalculateDiscount {
    @Override
    public void applyDiscount(OrderProduct orderProduct, PromotionResponse promotionResponse) {

        Long discount = orderProduct.getFinalPrice() * promotionResponse.amount() / 100;

        orderProduct.setFinalPrice(orderProduct.getFinalPrice() - discount);
        orderProduct.setSavingAmount(orderProduct.getSavingAmount() + (orderProduct.getTotalPrice() - orderProduct.getFinalPrice()));
    }
}
