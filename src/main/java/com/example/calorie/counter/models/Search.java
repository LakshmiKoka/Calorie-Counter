package com.example.calorie.counter.models;

public class Search {

    private String query;

    private String itemId;

    private String title;



    public Search() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Search{" +
                "query='" + query + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
