package com.sanberk.ekiptesin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainPageController {

    @GetMapping
    public String index(){
        return  "Welcome to Main Page";
    }
}
