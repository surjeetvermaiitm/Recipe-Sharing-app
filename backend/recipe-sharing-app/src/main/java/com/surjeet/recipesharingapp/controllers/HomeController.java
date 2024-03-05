package com.surjeet.recipesharingapp.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homeController(){
        return "Welcome from recipe sharing app";
    }

//    @PostMapping
//    @PutMapping
//    @DeleteMapping

}
