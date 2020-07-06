package com.rovo98.sbdd.demo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/test")
public class AnotherController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, this is a test message.";
    }
}
