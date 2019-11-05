package com.example.calorie.counter.models;

import java.util.Arrays;

public class SearchResult {


    public SearchResult() {
    }

    private Item[] results;
    private String baseUri;


    public Item[] getResults() {
        return results;
    }

    public void setResults(Item[] results) {
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
        return "SearchResult{" +
                "results=" + Arrays.toString(results) +
                ", baseUri='" + baseUri + '\'' +
                '}';
    }
}
