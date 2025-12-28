package org.example.repository;

import org.example.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VegetableRepository extends JpaRepository<Vegetable, Integer> {

    @Query("SELECT SUM(v.calories) FROM Vegetable v")
    Integer calculateTotalCalories();

    List<Vegetable> findByCaloriesBetween(int min, int max);
}