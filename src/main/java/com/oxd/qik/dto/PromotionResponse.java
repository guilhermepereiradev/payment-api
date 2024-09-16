package com.oxd.qik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PromotionResponse(String id, PromotionType type, Integer required_qty, Long price, Integer free_qty, Integer amount) {
}
