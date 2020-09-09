package com.ldce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index() {
        System.out.println("in home");
        return "index.html";
    }

}