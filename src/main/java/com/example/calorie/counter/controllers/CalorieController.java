package com.example.calorie.counter.controllers;

import com.example.calorie.counter.models.Item;
import com.example.calorie.counter.models.Search;
import com.example.calorie.counter.models.SearchResult;
import com.example.calorie.counter.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RequestMapping
public class CalorieController {


    private final SearchService searchService;

    @Autowired
    public CalorieController(SearchService searchService) {
        this.searchService = searchService;
    }


    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "Home");

        return "index";
    }

    @RequestMapping(value="search", method=RequestMethod.GET)
    public String search(Model model){

        model.addAttribute("title","Search");
        model.addAttribute(new Search());

         return "search";

    }

    @RequestMapping(value="search", method=RequestMethod.POST)
    public String result(Model model, @Valid Search search){


        model.addAttribute("title","Results");


        SearchResult searchResult = searchService.search(search.getQuery());

        model.addAttribute("result", searchResult);
        model.addAttribute("search", new Search());

        return "result";
    }


}
