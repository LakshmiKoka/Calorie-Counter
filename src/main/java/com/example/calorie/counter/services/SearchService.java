package com.example.calorie.counter.services;

import com.example.calorie.counter.model.ItemDetailsResult;
import com.example.calorie.counter.model.ItemSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private Environment environment;

    RestTemplate restTemplate = new RestTemplate();

    private static String url1 = "https://api.spoonacular.com/recipes/search?apiKey={apiKey}&query={query}";

    private static String url2 = "https://api.spoonacular.com/recipes/{id}/nutritionWidget.json?apiKey={apiKey}";

    public ItemSearchResult search(String searchTerm){


        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("apiKey", environment.getProperty("api.key"));
        queryParams.put("query", searchTerm);

        HttpEntity<String> entity = getStringHttpEntity();

        ResponseEntity<ItemSearchResult> responseObj = restTemplate.exchange(url1,
                HttpMethod.GET, entity, ItemSearchResult.class, queryParams);
        ItemSearchResult result = responseObj.getBody();

//        ItemSearchResult result = restTemplate.getForObject(url1, ItemSearchResult.class, queryParams);

        return result;
    }

    public ItemDetailsResult search2(String itemId){

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", itemId);
        queryParams.put("apiKey", environment.getProperty("api.key"));


        HttpEntity<String> entity = getStringHttpEntity();

        ResponseEntity<ItemDetailsResult> responseObj = restTemplate.exchange(url2,
                HttpMethod.GET, entity, ItemDetailsResult.class, queryParams);
        ItemDetailsResult result = responseObj.getBody();

//        ItemDetailsResult result = restTemplate.getForObject(url2, ItemDetailsResult.class, queryParams);

        return result;

    }

    private static HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }


}

