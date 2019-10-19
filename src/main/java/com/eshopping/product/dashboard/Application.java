package com.eshopping.product.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.eshopping.product.dashboard")
@EntityScan(basePackages = {"com.eshopping.product.dashboard.model"})
@EnableJpaRepositories(basePackages = "com.eshopping.product.dashboard")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
