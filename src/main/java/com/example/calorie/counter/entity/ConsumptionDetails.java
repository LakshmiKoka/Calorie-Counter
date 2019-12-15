package com.example.calorie.counter.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ConsumptionDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy="uuid2")
    @Column(columnDefinition="BINARY(16)")
    private UUID id;

    private  UUID consumptionId;

    private String categoryType;

    private String categoryValue;

    public ConsumptionDetails(){ }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(UUID consumptionId) {
        this.consumptionId = consumptionId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }
}
