package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // Hibernate маппит на таблицу Vegetables
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоинкремент ID
    private int id;
    private String name;
    private int calories;

    public Vegetable() {}  // Пустой конструктор для Hibernate

    public Vegetable(int id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    // Геттеры/сеттеры как раньше
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    @Override
    public String toString() {
        return "Vegetable{" + "id=" + id + ", name='" + name + '\'' + ", calories=" + calories + '}';
    }
}