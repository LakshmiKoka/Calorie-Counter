package com.example.calorie.counter.model;

public class Detail {

    private String title;
    private String amount;
    private Boolean indented;
    private int percentOfDailyNeeds;

    public Detail(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Boolean getIndented() {
        return indented;
    }

    public void setIndented(Boolean indented) {
        this.indented = indented;
    }

    public int getPercentOfDailyNeeds() {
        return percentOfDailyNeeds;
    }

    public void setPercentOfDailyNeeds(int percentOfDailyNeeds) {
        this.percentOfDailyNeeds = percentOfDailyNeeds;
    }

    @Override
    public String toString() {
        return "Values{" +
                "title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", indented=" + indented +
                ", percentOfDailyNeeds=" + percentOfDailyNeeds +
                '}';
    }
}