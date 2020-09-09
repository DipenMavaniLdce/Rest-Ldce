package com.ldce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
class HomeController {

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }
}