package com.gacha;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.gacha.entity")
public class GachaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GachaApplication.class, args);
    }
}