package com.permgen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CurrencyConfiguration {
    
    @Bean
    public RestTemplate restTemplate() {
        
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }
    
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }    
}
