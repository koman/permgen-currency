package com.permgen;

import java.net.InetSocketAddress;
import java.net.Proxy;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyApplication.class)
@WebIntegrationTest(randomPort = true)
public class ApplicationTests {

    @Value("${currency_url}")
    private String url;

    private RestTemplate template;

    @Before
    public void startup() {
        
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.142.60", 3128));
        clientHttpRequestFactory.setProxy(proxy);
        template = new RestTemplate(clientHttpRequestFactory);        
    }
    
    @Test
    public void resourceLoads() {
        
        Currency response = template.getForObject(url + "USD", Currency.class);
        assertEquals("USD", response.getBase());
    }
}
