package com.nimoh.commercetoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CommerceToyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceToyApplication.class, args);
    }

}
