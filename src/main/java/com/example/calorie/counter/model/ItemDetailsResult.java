package com.example.calorie.counter.model;

import java.util.List;

public class ItemDetailsResult {

    private String calories;
    private String carbs;
    private String fat;
    private String protein;
    private List<Detail> bad;
    private List<Detail> good;


    public ItemDetailsResult() {
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

    public List<Detail> getBad() {
        return bad;
    }

    public void setBad(List<Detail> bad) {
        this.bad = bad;
    }

    public List<Detail> getGood() {
        return good;
    }

    public void setGood(List<Detail> good) {
        this.good = good;
    }


    @Override
    public String toString() {
        return "ItemDetailsResult{" +
                "calories='" + calories + '\'' +
                ", carbs='" + carbs + '\'' +
                ", fat='" + fat + '\'' +
                ", protein='" + protein + '\'' +
                ", bad=" + bad +
                ", good=" + good +
                '}';
    }
}
