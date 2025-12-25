package org.example;
import java.util.List;

public class App 
{
    public static void main(String[] args) {
        try {
            System.out.println("Database location: " + DatabaseConnection.getDatabasePath());
            var vegetableDAO = new VegetableDAO();

            // Создание таблицы
            DatabaseConnection.createTable();

            // CREATE - добавление овощей
            System.out.println("=== CREATE VEGETABLES ===");
            vegetableDAO.create(new Vegetable(0, "Carrot", 41));
            vegetableDAO.create(new Vegetable(0, "Tomato", 18));
            vegetableDAO.create(new Vegetable(0, "Cucumber", 16));

            // READ - получение всех овощей
            System.out.println("\n=== ALL VEGETABLES ===");
            List<Vegetable> all = vegetableDAO.readAll();
            all.forEach(System.out::println);

            // READ - поиск по ID
            System.out.println("\n=== FIND VEGETABLE BY ID ===");
            Vegetable found = vegetableDAO.readById(1);  // Предполагаем ID=1 для Carrot
            System.out.println("Found: " + found);

            // UPDATE - обновление овоща
            System.out.println("\n=== UPDATE VEGETABLE ===");
            Vegetable toUpdate = vegetableDAO.readById(2);  // Tomato
            if (toUpdate != null) {
                Vegetable updated = new Vegetable(toUpdate.getId(), "Updated Tomato", 20);
                vegetableDAO.update(updated);
            }

            // Чтение после обновления
            System.out.println("\n=== VEGETABLES AFTER UPDATE ===");
            vegetableDAO.readAll().forEach(System.out::println);

            // DELETE - удаление овоща
            System.out.println("\n=== DELETE VEGETABLE ===");
            vegetableDAO.delete(3);  // Cucumber

            // Финальный список
            System.out.println("\n=== FINAL VEGETABLES LIST ===");
            vegetableDAO.readAll().forEach(System.out::println);

            // Тематические операции
            System.out.println("\n=== SALAD CALORIES ===");
            System.out.println("Total salad calories: " + vegetableDAO.calculateSaladCalories());

            System.out.println("\n=== SORTED BY CALORIES ===");
            List<Vegetable> sorted = vegetableDAO.sortByCalories();
            sorted.forEach(System.out::println);

            System.out.println("\n=== VEGETABLES IN CALORIES RANGE (15-25) ===");
            List<Vegetable> inRange = vegetableDAO.findByCaloriesRange(15, 25);
            inRange.forEach(System.out::println);

            vegetableDAO.close();  // Если добавишь метод close в DAO для закрытия ресурсов

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
