package com.oxd.qik.dto;

import java.util.List;

public record ProductDTO(String id, String name, Long price, List<PromotionDTO> promotions) {
}
