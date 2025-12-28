package org.example.controller;

import org.example.entity.Vegetable;
import org.example.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private VegetableService service;

    @GetMapping("/")
    public String home(Model model) {
        List<Vegetable> veggies = service.getAll();
        int totalCalories = service.calculateSaladCalories();

        model.addAttribute("message", "Vegetables Project работает!");
        model.addAttribute("veggiesCount", veggies.size());
        model.addAttribute("totalCalories", totalCalories);

        return "index";
    }

}