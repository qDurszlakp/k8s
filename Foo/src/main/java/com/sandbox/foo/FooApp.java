package com.sandbox.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FooApp {

    public static void main(String[] args) {
        SpringApplication.run(FooApp.class, args);
    }

}
