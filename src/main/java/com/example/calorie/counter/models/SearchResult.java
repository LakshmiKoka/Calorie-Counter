package com.example.calorie.counter.models;

import java.util.Arrays;
import java.util.List;

public class SearchResult {


    public SearchResult() {
    }

    private List<Item> results;
    private String baseUri;


    private String calories;
    private String carbs;
    private String fat;
    private String protein;




    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "results=" + results +
                ", baseUri='" + baseUri + '\'' +
                ", calories='" + calories + '\'' +
                ", carbs='" + carbs + '\'' +
                ", fat='" + fat + '\'' +
                ", protein='" + protein + '\'' +
                '}';
    }
}
