package com.example.calorie.counter.controllers;

import com.example.calorie.counter.entity.Consumption;
import com.example.calorie.counter.entity.ConsumptionDetails;
import com.example.calorie.counter.entity.FoodItem;
import com.example.calorie.counter.model.*;
import com.example.calorie.counter.repository.ConsumptionDao;
import com.example.calorie.counter.repository.ConsumptionDetailsDao;
import com.example.calorie.counter.repository.FoodItemDao;
import com.example.calorie.counter.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class CalorieController {

    @Autowired
    private ConsumptionDao consumptionDao;

    @Autowired
    private FoodItemDao fooditemDao;

    @Autowired
    private ConsumptionDetailsDao consumptiondetailsDao;

    private final SearchService searchService;

    private final HttpSession httpSession;

//    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddTHH:mm");

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
        httpSession.setAttribute("itemSelectedId", search.getItemId());

        search.setTitle(itemSelected.getTitle());

        model.addAttribute("result", itemDetailsResult);
        model.addAttribute("search", search);

        return "item-details";
    }

    @RequestMapping(value="save", method=RequestMethod.POST)
    public String save(Model model, Search search) throws ParseException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ItemSearchResult itemSearchResults = (ItemSearchResult) httpSession.getAttribute("searchResults");
        String itemId = (String)httpSession.getAttribute("itemSelectedId");

        Item itemSelected = itemSearchResults.getResults()
                .stream()
                .filter(item -> item.getId().equalsIgnoreCase(itemId))
                .findAny()
                .orElse(null);

        ItemDetailsResult itemDetailsResult = searchService.search2(itemId);

//      Saving details to consumption table

        Consumption consumption = new Consumption();
        consumption.setDateTime(OffsetDateTime.ofInstant(Instant.parse(search.getDateTime()+":00Z"), ZoneId.systemDefault()));
        consumption.setItemId(itemId);
        consumption.setUserId(authentication.getName());
        consumptionDao.save(consumption);

//        saving details to fooditem table

        FoodItem fooditem = new FoodItem();
        fooditem.setItemId(itemId);
        fooditem.setItemTitle(itemSelected.getTitle());
        fooditem.setImage(itemSelected.getImage());
        fooditemDao.save(fooditem);

//        saving details to consumptiondetails table

        List<ConsumptionDetails> consumptionList = new ArrayList<>();

        ConsumptionDetails calories = new ConsumptionDetails();
        calories.setConsumptionId(consumption.getId());
        calories.setCategoryType("Calories");
        calories.setCategoryValue(itemDetailsResult.getCalories());
        consumptionList.add(calories);

        ConsumptionDetails carbs = new ConsumptionDetails();
        carbs.setConsumptionId(consumption.getId());
        carbs.setCategoryType("Carbs");
        carbs.setCategoryValue(itemDetailsResult.getCarbs());
        consumptionList.add(carbs);

        ConsumptionDetails fat = new ConsumptionDetails();
        fat.setConsumptionId(consumption.getId());
        fat.setCategoryType("Fat");
        fat.setCategoryValue(itemDetailsResult.getFat());
        consumptionList.add(fat);

        ConsumptionDetails protein = new ConsumptionDetails();
        protein.setConsumptionId(consumption.getId());
        protein.setCategoryType("Protein");
        protein.setCategoryValue(itemDetailsResult.getProtein());
        consumptionList.add(protein);
        consumptiondetailsDao.saveAll(consumptionList);

        return "redirect:/daily-calorie";

    }
    @RequestMapping(value = "daily-calorie", method=RequestMethod.GET)
    public String viewDailyCalorie(Model model){

        OffsetDateTime date = OffsetDateTime.now().minusDays(100);
        List<Consumption> consumptionList = consumptionDao.findByDateTimeAfter(date);
        List<DateTitleInfo> dateTitleInfos = new ArrayList<>();

        if(consumptionList.size() > 0) {
            List<String> itemIds = consumptionList.stream()
                    .map(consumption -> consumption.getItemId())
                    .collect(Collectors.toList());

            List<FoodItem> foodItems = fooditemDao.findByItemIdIn(itemIds);

            List<UUID> consumptionIds = consumptionList.stream()
                    .map(consumption -> consumption.getId())
                    .collect(Collectors.toList());

//            List<ConsumptionDetails> consumptionDetails = consumptiondetailsDao.findByConsumptionIdIn(consumptionIds);


            for (Consumption consumption : consumptionList) {
                DateTitleInfo dateTitleInfo = new DateTitleInfo();
                dateTitleInfo.setDate(consumption.getDateTime());

                Optional<FoodItem> food = foodItems.stream()
                        .filter(foodItem -> foodItem.getItemId().equals(consumption.getItemId()))
                        .findFirst();
                if (food.isPresent()) {
                    dateTitleInfo.setTitle(food.get().getItemTitle());

                    ConsumptionDetails calories = consumptiondetailsDao
                            .findByConsumptionIdAndCategoryType(consumption.getId(), "Calories");

                    dateTitleInfo.setCalories(calories.getCategoryValue());

                    ConsumptionDetails carbs = consumptiondetailsDao
                            .findByConsumptionIdAndCategoryType(consumption.getId(), "Carbs");

                    dateTitleInfo.setCarbs(carbs.getCategoryValue());

                    ConsumptionDetails fat = consumptiondetailsDao
                            .findByConsumptionIdAndCategoryType(consumption.getId(), "Fat");

                    dateTitleInfo.setFat(fat.getCategoryValue());

                    ConsumptionDetails protein = consumptiondetailsDao
                            .findByConsumptionIdAndCategoryType(consumption.getId(), "Protein");

                    dateTitleInfo.setProtein(protein.getCategoryValue());

                }

                dateTitleInfos.add(dateTitleInfo);
            }
        }
    model.addAttribute("title","Daily Calorie Data");
    model.addAttribute("dateTitleInfos", dateTitleInfos);

    return "today";
    }
}
