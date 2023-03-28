package org.koimiam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("---- KOIMIAM ----");
        System.out.println("Generateur de menu");

        SpringApplication.run(Application.class, args);
    }
}