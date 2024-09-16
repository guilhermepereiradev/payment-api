package com.oxd.qik.controller;

import com.oxd.qik.dto.OrderRequest;
import com.oxd.qik.dto.OrderResponse;
import com.oxd.qik.service.OrderService;
import com.oxd.qik.service.ex.EntityNotFoundException;
import com.oxd.qik.service.ex.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderRequest request) {
        try {
            var order = orderService.save(request);
            return ResponseEntity.ok(new OrderResponse(order));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/apply-discount")
    public ResponseEntity<?> applyDiscount(@PathVariable Long id) {
        try {
            var order = orderService.applyDiscount(id);
            return ResponseEntity.ok(new OrderResponse(order));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        var ordersResponse = orderService.findAll()
                .stream()
                .map(OrderResponse::new)
                .toList();
        return ResponseEntity.ok(ordersResponse);
    }
}
