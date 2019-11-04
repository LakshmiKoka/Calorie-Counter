package com.example.calorie.counter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class CalorieController {

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "Home");

        return "index";
    }

    @RequestMapping(value="search", method=RequestMethod.GET)
    public String search(Model model){

        model.addAttribute("title","Search");

         return "search";

    }
}
