package com.oxd.qik.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@IdClass(OrderProductId.class)
@Table(name = "tb_order_product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderProduct {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @Id
    @EqualsAndHashCode.Include
    private String productId;
    private Integer quantity;
    private Long price;
    private Long totalPrice;
    private Long finalPrice;
    private Long savingAmount;

    public void calculateTotalPrice(){
        setTotalPrice(getPrice() * getQuantity());
    }
}


