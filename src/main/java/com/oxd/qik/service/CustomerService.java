package com.oxd.qik.service;

import com.oxd.qik.model.Customer;
import com.oxd.qik.repository.CustomerRepository;
import com.oxd.qik.service.ex.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Customer not found for id: %d", id)
                ));
    }
}
