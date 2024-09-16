package com.oxd.qik.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalAmount;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products = new ArrayList<>();
    private Boolean discountApplied = false;
    private Long finalPrice;
    private Long savingAmount;

    public void calculateTotalAmount(){
        Long auxTotalAmount = 0L;
        for (OrderProduct orderProduct : getProducts()) {
            auxTotalAmount += orderProduct.getTotalPrice();
        }
        setTotalAmount(auxTotalAmount);
    }

    public void calculateFinalPrice(){
        Long auxFinalPrice = 0L;
        for (OrderProduct orderProduct : getProducts()) {
            auxFinalPrice += orderProduct.getFinalPrice();
        }
        setFinalPrice(auxFinalPrice);
    }

    public void calculateSavinAmout() {
        Long auxSavingAmount = 0L;
        for (OrderProduct orderProduct : getProducts()) {
            auxSavingAmount += orderProduct.getSavingAmount();
        }
        setSavingAmount(auxSavingAmount);
    }
}
