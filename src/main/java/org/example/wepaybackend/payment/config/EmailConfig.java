
package org.example.wepaybackend.payment.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class EmailConfig{

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
