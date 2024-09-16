package com.oxd.qik.client;

import com.oxd.qik.dto.ProductResponse;
import com.oxd.qik.dto.ProductResume;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/products")
public interface ProductsClient {

    @GetExchange()
    List<ProductResume> getProducts();

    @GetExchange("/{id}")
    ProductResponse getProductById(@PathVariable String id);
}
