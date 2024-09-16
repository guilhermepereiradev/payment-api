package com.oxd.qik.service.discounts.impl;

import com.oxd.qik.dto.PromotionResponse;
import com.oxd.qik.model.OrderProduct;
import com.oxd.qik.service.discounts.CalculateDiscount;


public class CalculateBuyXGetYFree implements CalculateDiscount {
    @Override
    public void applyDiscount(OrderProduct orderProduct, PromotionResponse promotionResponse) {

        if (orderProduct.getQuantity() >= promotionResponse.required_qty()) {
            Integer freeQuantity = orderProduct.getQuantity() / promotionResponse.required_qty() * promotionResponse.free_qty();
            Long freeQuantityPrice = orderProduct.getPrice() * freeQuantity;
            orderProduct.setFinalPrice(orderProduct.getFinalPrice() - freeQuantityPrice);
            orderProduct.setSavingAmount(orderProduct.getSavingAmount() + freeQuantityPrice);
        }
    }
}
