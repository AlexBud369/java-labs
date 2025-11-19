package Task2;

import java.time.LocalDate;
import java.util.*;

public class Task02 {
    public static void main(String[] args) {
        WarehouseManager warehouseSystem = new WarehouseSystem();

        // Создание складов
        warehouseSystem.addWarehouse("Основной склад", "ул. Центральная, 1");
        warehouseSystem.addWarehouse("Дополнительный склад", "ул. Заводская, 15");

        // Создание поставщиков
        warehouseSystem.addSupplier("ООО 'ТехноПоставка'", "ул. Поставщиков, 10", "+7-999-111-11-11");
        warehouseSystem.addSupplier("ИП Иванов", "ул. Торговая, 25", "+7-999-222-22-22");

        // Добавление товаров
        warehouseSystem.addProduct("Ноутбук Dell", "шт.", 75000.0);
        warehouseSystem.addProduct("Мышь компьютерная", "шт.", 2500.0);
        warehouseSystem.addProduct("Клавиатура механическая", "шт.", 5500.0);
        warehouseSystem.addProduct("Монитор 27\"", "шт.", 35000.0);

        // 1. Форма ввода накладной - ПРИХОД
        System.out.println("=== ФОРМА ВВОДА НАКЛАДНОЙ - ПРИХОД ===");
        Document invoice = warehouseSystem.createInvoice("ПР-001", LocalDate.of(2024, 1, 15), "ПРИХОД");
        invoice.setSupplier("ООО 'ТехноПоставка'");
        invoice.setToWarehouse("Основной склад");
        invoice.setBasis("Договор поставки №123 от 10.01.2024");

        // Добавление товаров в накладную
        invoice.addItem("Ноутбук Dell", 5);
        invoice.addItem("Мышь компьютерная", 20);
        invoice.addItem("Клавиатура механическая", 10);

        // Обработка накладной
        warehouseSystem.processInvoice(invoice);

        // Вывод накладной
        System.out.println(invoice);
        System.out.printf("ИТОГО: %.2f руб.\n", invoice.getTotalAmount());

        // 2. Перемещение между складами
        System.out.println("\n\n=== ФОРМА ВВОДА НАКЛАДНОЙ - ПЕРЕМЕЩЕНИЕ ===");
        Document transferInvoice = warehouseSystem.createInvoice("ПЕР-001", LocalDate.now(), "ПЕРЕМЕЩЕНИЕ");
        transferInvoice.setFromWarehouse("Основной склад");
        transferInvoice.setToWarehouse("Дополнительный склад");
        transferInvoice.setBasis("Внутреннее перемещение");

        transferInvoice.addItem("Ноутбук Dell", 2);
        transferInvoice.addItem("Мышь компьютерная", 5);

        warehouseSystem.processInvoice(transferInvoice);
        System.out.println("Перемещение выполнено успешно");

        // 3. Список всех товаров на складе
        System.out.println("\n\n=== ВСЕ ТОВАРЫ НА СКЛАДАХ ===");
        Map<String, Map<String, Integer>> allInventory = warehouseSystem.getAllInventory();
        allInventory.forEach((warehouse, inventory) -> {
            System.out.println("\n" + warehouse + ":");
            if (inventory.isEmpty()) {
                System.out.println("  Склад пуст");
            } else {
                inventory.forEach((product, quantity) ->
                        System.out.println("  " + product + ": " + quantity + " шт."));
            }
        });

        // 4. Список всех внешних поставщиков
        System.out.println("\n\n=== ВСЕ ВНЕШНИЕ ПОСТАВЩИКИ ===");
        List<String> suppliers = warehouseSystem.getAllSuppliers();
        suppliers.forEach(System.out::println);

        // 5. Поиск товара по наименованию
        System.out.println("\n\n=== ПОИСК ТОВАРА 'МЫШЬ' ===");
        Map<String, Integer> searchResults = warehouseSystem.searchProduct("мышь");
        if (searchResults.isEmpty()) {
            System.out.println("Товар не найден");
        } else {
            searchResults.forEach((warehouse, quantity) ->
                    System.out.println(warehouse + ": " + quantity + " шт."));
        }

        // 6. Расходная накладная
        System.out.println("\n\n=== ФОРМА ВВОДА НАКЛАДНОЙ - РАСХОД ===");
        Document outgoingInvoice = warehouseSystem.createInvoice("РСХ-001", LocalDate.now(), "РАСХОД");
        outgoingInvoice.setFromWarehouse("Основной склад");
        outgoingInvoice.setBasis("Заказ клиента №456");

        outgoingInvoice.addItem("Мышь компьютерная", 3);
        outgoingInvoice.addItem("Клавиатура механическая", 2);

        warehouseSystem.processInvoice(outgoingInvoice);
        System.out.println("Отгрузка выполнена успешно");

        // Финальное состояние складов
        System.out.println("\n\n=== ФИНАЛЬНОЕ СОСТОЯНИЕ СКЛАДОВ ===");
        Map<String, Map<String, Integer>> finalInventory = warehouseSystem.getAllInventory();
        finalInventory.forEach((warehouse, inventory) -> {
            System.out.println("\n" + warehouse + ":");
            inventory.forEach((product, quantity) ->
                    System.out.println("  " + product + ": " + quantity + " шт."));
        });
    }
}