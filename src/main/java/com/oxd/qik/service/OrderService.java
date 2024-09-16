package com.oxd.qik.service;

import com.oxd.qik.client.ProductsClient;
import com.oxd.qik.dto.OrderRequest;
import com.oxd.qik.dto.ProductRequest;
import com.oxd.qik.dto.PromotionResponse;
import com.oxd.qik.model.Order;
import com.oxd.qik.model.OrderProduct;
import com.oxd.qik.repository.OrderProductRepository;
import com.oxd.qik.repository.OrderRepository;
import com.oxd.qik.service.discounts.factory.CalculateDiscountFactory;
import com.oxd.qik.service.ex.EntityNotFoundException;
import com.oxd.qik.service.ex.ValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductsClient productsClient;
    private final CustomerService customerService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for id: " + id));
    }

    @Transactional
    public Order save(OrderRequest request) {
        var order = new Order();
        order.setCustomer(customerService.findById(request.customer_id()));
        orderRepository.save(order);

        associateProducts(order, request.products());

        order.calculateTotalAmount();
        order.setFinalPrice(order.getTotalAmount());
        order.setSavingAmount(0L);
        return order;
    }

    private void associateProducts(Order order, List<ProductRequest> request) {
        var productsResponse = productsClient.getProducts()
                .stream()
                .filter(p -> request
                        .stream()
                        .map(ProductRequest::id)
                        .toList().contains(p.id())
                ).toList();

        for (ProductRequest productRequest : request) {
            var product = productsResponse.stream()
                    .filter(p -> p.id().equals(productRequest.id()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + productRequest.id()));

            var orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProductId(product.id());
            orderProduct.setQuantity(productRequest.quantity());
            orderProduct.setPrice(product.price());
            orderProduct.calculateTotalPrice();
            orderProduct.setFinalPrice(orderProduct.getTotalPrice());
            orderProduct.setSavingAmount(0L);
            order.getProducts().add(orderProductRepository.save(orderProduct));
        }
    }

    @Transactional
    public Order applyDiscount(Long id) {
        Order order = findById(id);
        validadesApplicableDiscount(order);

        calculateDiscounts(order);

        order.setDiscountApplied(true);
        order.calculateFinalPrice();
        order.calculateSavinAmout();
        return order;
    }

    private void calculateDiscounts(Order order) {
        for (OrderProduct orderProduct : order.getProducts()) {
            var productResponse = productsClient.getProductById(orderProduct.getProductId());

            for (PromotionResponse promotionResponse : productResponse.promotions()) {
                var calculateDiscount = CalculateDiscountFactory.createCalculateDiscount(promotionResponse.type());
                calculateDiscount.applyDiscount(orderProduct, promotionResponse);
            }
        }
    }

    private void validadesApplicableDiscount(Order order) {
        if (order.getDiscountApplied()) {
            throw new ValidationException("Discount already applied!");
        }
    }
}
