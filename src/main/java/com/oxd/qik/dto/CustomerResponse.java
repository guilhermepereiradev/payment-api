package com.oxd.qik.dto;

import com.oxd.qik.model.Customer;

public record CustomerResponse(Long id, String name, String email) {
    public CustomerResponse(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail());
    }
}
