package com.permgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class CurrencyApplication {

    @Value("${currency_url}")
    private String CURRENCY_URL;

    @Autowired
    private RestTemplate client;

    @RequestMapping(value = "/{currency}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Currency currentRates(@PathVariable String currency) {

        String url = CURRENCY_URL + currency;   

        return client.getForObject(url, Currency.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrencyApplication.class, args);
    }

}
