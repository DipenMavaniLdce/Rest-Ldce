package com.ldce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(path = "/",method =RequestMethod.GET)
    public String index() {
        System.out.println("in home");
        return "index";
    }

}