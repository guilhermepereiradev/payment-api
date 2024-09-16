package com.oxd.qik.model;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OrderProductId {

    @ManyToOne
    private Order order;
    private String productId;
}
