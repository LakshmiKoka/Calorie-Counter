package com.example.calorie.counter.services;

import com.example.calorie.counter.model.ItemDetailsResult;
import com.example.calorie.counter.model.ItemSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private Environment environment;


    private static String url1 = "https://api.spoonacular.com/recipes/search?apiKey={apiKey}&query={query}";

    private static String url2 = "https://api.spoonacular.com/recipes/{id}/nutritionWidget.json?apiKey={apiKey}";

    public ItemSearchResult search(String searchTerm){

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("apiKey", environment.getProperty("api.key"));
        queryParams.put("query", searchTerm);

        ItemSearchResult result = restTemplate.getForObject(url1, ItemSearchResult.class, queryParams);

        return result;


    }

    public ItemDetailsResult search2(String itemId){

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", itemId);
        queryParams.put("apiKey", environment.getProperty("api.key"));

        ItemDetailsResult result = restTemplate.getForObject(url2, ItemDetailsResult.class, queryParams);

        return result;

    }
}

