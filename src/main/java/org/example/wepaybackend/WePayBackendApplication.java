package org.example.wepaybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class WePayBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WePayBackendApplication.class, args);
    }

}