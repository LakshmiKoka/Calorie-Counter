package com.example.calorie.counter.controllers;

import com.example.calorie.counter.entity.Consumption;
import com.example.calorie.counter.model.Item;
import com.example.calorie.counter.model.ItemDetailsResult;
import com.example.calorie.counter.model.ItemSearchResult;
import com.example.calorie.counter.model.Search;
import com.example.calorie.counter.repository.ConsumptionDao;
import com.example.calorie.counter.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class CalorieController {

    @Autowired
    private ConsumptionDao consumptionDao;

    private final SearchService searchService;

    private final HttpSession httpSession;

    @Autowired
    public CalorieController(SearchService searchService, HttpSession httpSession) {
        this.searchService = searchService;
        this.httpSession = httpSession;
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
    public String result(Model model, Search search){


        model.addAttribute("title","Results");


        ItemSearchResult itemSearchResult = searchService.search(search.getQuery());

        httpSession.setAttribute("searchResults", itemSearchResult);

        model.addAttribute("result", itemSearchResult);
        model.addAttribute("search", new Search());

        return "search-result";
    }

    @RequestMapping(value="selected", method=RequestMethod.POST)
    public String selected(Model model, Search search){


        ItemSearchResult itemSearchResults = (ItemSearchResult) httpSession.getAttribute("searchResults");

        Item itemSelected = itemSearchResults.getResults()
                .stream()
                .filter(item -> item.getId().equalsIgnoreCase(search.getItemId()))
                .findAny()
                .orElse(null);


        model.addAttribute("title","Results");

        ItemDetailsResult itemDetailsResult = searchService.search2(search.getItemId());

        httpSession.setAttribute("itemSelected", itemDetailsResult);

        search.setTitle(itemSelected.getTitle());
        search.setItemId(itemSelected.getId());
        model.addAttribute("result", itemDetailsResult);
        model.addAttribute("search", search);

        return "item-details";
    }

    @RequestMapping(value="save", method=RequestMethod.POST)
    public String save(Model model, Search search){

        System.out.println(search.getDateTime());
        Consumption consumption = new Consumption();
        consumption.setDateTime(search.getDateTime());
        consumption.setItemId(search.getItemId());
        consumptionDao.save(consumption);


        return "saved";

    }
}
