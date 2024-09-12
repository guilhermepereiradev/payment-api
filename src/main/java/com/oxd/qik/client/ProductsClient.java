package com.oxd.qik.client;

import com.oxd.qik.dto.ProductDTO;
import com.oxd.qik.dto.ProductResumeDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/products")
public interface ProductsClient {

    @GetExchange()
    List<ProductResumeDTO> getProducts();

    @GetExchange("/{id}")
    ProductDTO getProductById(@PathVariable String id);
}
