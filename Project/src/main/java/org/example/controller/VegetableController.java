package org.example.controller;

import org.example.entity.Vegetable;
import org.example.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/veggies")
public class VegetableController {
    @Autowired
    private VegetableService service;

    @GetMapping
    public String listVegetables(Model model) {
        model.addAttribute("veggies", service.getAll());
        model.addAttribute("totalCalories", service.calculateSaladCalories());
        return "vegetables";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("vegetable", new Vegetable());
        return "add-vegetable";
    }

    @PostMapping("/add")
    public String addVegetable(@ModelAttribute Vegetable vegetable) {
        if (vegetable.getCalories() < 0) {
            return "add-vegetable";
        }
        service.save(vegetable);
        return "redirect:/veggies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Vegetable> veg = service.getById(id);
        if (veg.isEmpty()) {
            return "redirect:/veggies";
        }
        model.addAttribute("vegetable", veg.get());
        return "edit-vegetable";
    }

    @PostMapping("/edit/{id}")
    public String editVegetable(@PathVariable int id, @ModelAttribute Vegetable vegetable) {
        if (vegetable.getCalories() < 0) {
            return "edit-vegetable";
        }
        vegetable.setId(id);
        service.save(vegetable);
        return "redirect:/veggies";
    }

    @GetMapping("/delete/{id}")
    public String deleteVegetable(@PathVariable int id) {
        service.delete(id);
        return "redirect:/veggies";
    }

    @GetMapping("/sorted")
    public String sortedVegetables(Model model) {
        model.addAttribute("veggies", service.sortByCalories());
        model.addAttribute("totalCalories", service.calculateSaladCalories());
        model.addAttribute("message", "Сортировка по калорийности");
        return "vegetables";
    }

    @GetMapping("/range")
    public String rangeVegetables(@RequestParam int min, @RequestParam int max, Model model) {
        if (min < 0 || max < 0 || min > max) {
            model.addAttribute("error", "Некорректный диапазон");
            model.addAttribute("veggies", service.getAll());
        } else {
            model.addAttribute("veggies", service.findByRange(min, max));
            model.addAttribute("message", "Овощи в диапазоне " + min + "-" + max);
        }
        model.addAttribute("totalCalories", service.calculateSaladCalories());
        return "vegetables";
    }
}