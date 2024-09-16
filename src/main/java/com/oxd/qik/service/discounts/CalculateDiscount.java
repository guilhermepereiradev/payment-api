package com.oxd.qik.service.discounts;

import com.oxd.qik.dto.PromotionResponse;
import com.oxd.qik.model.OrderProduct;

public interface CalculateDiscount {

    void applyDiscount(OrderProduct orderProduct, PromotionResponse productResponse);
}
