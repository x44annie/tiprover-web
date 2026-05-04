package com.note.tiprover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TiproverWebApplication {

    static void main(String[] args) {
        SpringApplication.run(TiproverWebApplication.class, args);
    }

}
