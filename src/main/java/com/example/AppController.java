package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {


    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }


    @GetMapping("/add")
    public int add() {
        return service.add(2, 3);
    }


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }
}
