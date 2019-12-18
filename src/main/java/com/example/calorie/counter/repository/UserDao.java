package com.example.calorie.counter.repository;

import com.example.calorie.counter.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserDao extends CrudRepository <User, UUID> {
    User findByUsername(String username);
}
