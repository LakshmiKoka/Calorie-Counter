package com.example.calorie.counter.repository;

import com.example.calorie.counter.entity.Consumption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sun.awt.image.IntegerComponentRaster;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface ConsumptionDao extends CrudRepository <Consumption, UUID>{
}
