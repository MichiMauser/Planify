package com.example.planify;

import com.example.planify.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class PlanifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanifyApplication.class, args);
    }
//    @Bean
//    CommandLineRunner run(MockDataService mockDataService) {
//        return args -> {
//            System.out.println("Printing Mock Data:");
//            System.out.println(mockDataService.getUsers());
//            System.out.println(mockDataService.getNotebooks().isEmpty());
//        };
//    }

}
