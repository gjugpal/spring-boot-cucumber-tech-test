package com.geek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com/geek/controller")
@EnableAutoConfiguration
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
