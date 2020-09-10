package com.ldce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
class HomeController {
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }
}