package com.oxd.qik.dto;

import java.util.List;

public record ProductResponse(String id, String name, Long price, List<PromotionResponse> promotions) {
}
