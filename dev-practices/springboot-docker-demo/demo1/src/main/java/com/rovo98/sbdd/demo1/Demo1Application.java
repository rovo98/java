package com.rovo98.sbdd.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}

@RestController
@RequestMapping("/app")
class TestController {
    @GetMapping("/sayHello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello " + name + " from demo1. " + new Date().toString();
    }
}
