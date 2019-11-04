package com.example.calorie.counter.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fooditem {

    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private String searchTerm;

   // @DateTimeFormat
    private String dateTime;


    public Fooditem(){

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
