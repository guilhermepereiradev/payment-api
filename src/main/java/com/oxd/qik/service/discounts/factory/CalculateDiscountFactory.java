package com.oxd.qik.service.discounts.factory;

import com.oxd.qik.dto.PromotionType;
import com.oxd.qik.service.discounts.CalculateDiscount;
import com.oxd.qik.service.discounts.impl.CalculateBuyXGetYFree;
import com.oxd.qik.service.discounts.impl.CalculateFlatPercent;
import com.oxd.qik.service.discounts.impl.CalculateQtyBasedPriceOverride;

public class CalculateDiscountFactory {

    private CalculateDiscountFactory(){}

    public static CalculateDiscount createCalculateDiscount(PromotionType promotionType){
        switch (promotionType) {
            case BUY_X_GET_Y_FREE -> {
                return new CalculateBuyXGetYFree();
            }
            case FLAT_PERCENT -> {
                return new CalculateFlatPercent();
            }
            case QTY_BASED_PRICE_OVERRIDE -> {
                return new CalculateQtyBasedPriceOverride();
            }
            default -> throw new IllegalArgumentException("Unknown discount type");
        }
    }
}
