package com.example.calorie.counter.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calorie {

    @Id
    @GeneratedValue
    private int id;

    private int foodId;

    private String calorieType;

    private  int value;

    public Calorie(){

    }

    public int getId() {
        return id;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getCalorieType() {
        return calorieType;
    }

    public void setCalorieType(String calorieType) {
        this.calorieType = calorieType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
