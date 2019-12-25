package com.codegym.zing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan("com.codegym.zing.model")
public class ZingApplication implements WebMvcConfigurer{
    public static void main(String[] args) {
        SpringApplication.run(ZingApplication.class, args);
    }
}
