package com.example.calorie.counter.model;

import java.util.List;

public class ItemSearchResult {


    public ItemSearchResult() {
    }

    private List<Item> results;
    private String baseUri;


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

    @Override
    public String toString() {
        return "ItemSearchResult{" +
                "results=" + results +
                ", baseUri='" + baseUri + '\'' +
                '}';
    }
}
