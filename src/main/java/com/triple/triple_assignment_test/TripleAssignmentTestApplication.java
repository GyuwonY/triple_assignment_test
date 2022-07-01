package com.triple.triple_assignment_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripleAssignmentTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripleAssignmentTestApplication.class, args);
    }

}
