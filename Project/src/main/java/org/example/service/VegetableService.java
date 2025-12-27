package org.example.service;

import org.example.entity.Vegetable;
import org.example.repository.VegetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VegetableService {
    @Autowired
    private VegetableRepository repo;

    public List<Vegetable> getAll() {
        return repo.findAll();
    }

    public Optional<Vegetable> getById(int id) {
        return repo.findById(id);
    }

    public Vegetable save(Vegetable veg) {
        return repo.save(veg);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public int calculateSaladCalories() {
        Integer total = repo.calculateTotalCalories();
        return total != null ? total : 0;
    }

    public List<Vegetable> sortByCalories() {
        return repo.findAll().stream()
                .sorted((v1, v2) -> Integer.compare(v1.getCalories(), v2.getCalories()))
                .toList();  // Или @Query с ORDER BY
    }

    public List<Vegetable> findByRange(int min, int max) {
        return repo.findByCaloriesBetween(min, max);
    }
}