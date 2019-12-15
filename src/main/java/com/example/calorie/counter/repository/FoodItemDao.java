package com.example.calorie.counter.repository;

import com.example.calorie.counter.entity.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodItemDao extends CrudRepository <FoodItem, UUID> {

    List<FoodItem> findByItemIdIn(List<String> itemIds);
}
