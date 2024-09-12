package com.oxd.qik.config;

import com.oxd.qik.client.ProductsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {

    @Value("${qik.config.services.products}")
    private String baseUrl;

    @Bean
    public ProductsClient productsClient() {
        return HttpServiceProxyFactory
                .builderFor(
                        WebClientAdapter
                                .create(WebClient.create(baseUrl))
                ).build()
                .createClient(ProductsClient.class);
    }
}
