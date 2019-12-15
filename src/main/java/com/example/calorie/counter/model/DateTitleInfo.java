package com.example.calorie.counter.model;


import java.time.OffsetDateTime;


public class DateTitleInfo {

    private OffsetDateTime date;

    private String title;

    private String image;

    public DateTitleInfo() {
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
