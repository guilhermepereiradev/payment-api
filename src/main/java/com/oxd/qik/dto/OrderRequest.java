package com.oxd.qik.dto;

import java.util.List;

public record OrderRequest(Long customer_id, List<ProductRequest> products) {
}
