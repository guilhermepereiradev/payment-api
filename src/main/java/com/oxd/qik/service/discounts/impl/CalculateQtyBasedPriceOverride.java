package com.oxd.qik.service.discounts.impl;

import com.oxd.qik.dto.PromotionResponse;
import com.oxd.qik.model.OrderProduct;
import com.oxd.qik.service.discounts.CalculateDiscount;

public class CalculateQtyBasedPriceOverride implements CalculateDiscount {
    @Override
    public void applyDiscount(OrderProduct orderProduct, PromotionResponse promotionResponse) {

        if (orderProduct.getQuantity() >= promotionResponse.required_qty()) {
            orderProduct.setFinalPrice(promotionResponse.price());
            orderProduct.setSavingAmount(orderProduct.getSavingAmount() + (orderProduct.getTotalPrice() - orderProduct.getFinalPrice()));
        }
    }
}
