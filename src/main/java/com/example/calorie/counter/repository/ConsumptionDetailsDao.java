package com.example.calorie.counter.repository;

import com.example.calorie.counter.entity.Consumption;
import com.example.calorie.counter.entity.ConsumptionDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sun.awt.image.IntegerComponentRaster;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionDetailsDao extends CrudRepository <ConsumptionDetails, UUID>{
    List<ConsumptionDetails> findByConsumptionIdIn(List<UUID> consumptionIds);
    ConsumptionDetails findByConsumptionIdAndCategoryType(UUID consumptionId, String categoryType);
}
