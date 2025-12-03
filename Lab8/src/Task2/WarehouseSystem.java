package Task2;

import java.time.LocalDate;
import java.util.*;

public class WarehouseSystem implements WarehouseManager {
    private final Map<String, Warehouse> warehouses;
    private final Map<String, Supplier> suppliers;
    private final Map<String, Product> products;
    private final List<Document> invoices;
    private final Map<String, Map<String, Integer>> inventory;

    public WarehouseSystem() {
        this.warehouses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.products = new HashMap<>();
        this.invoices = new ArrayList<>();
        this.inventory = new HashMap<>();
    }

    @Override
    public void addWarehouse(String name, String address) {
        if (name == null || name.trim().isEmpty() || address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Название и адрес склада не могут быть пустыми");
        }
        warehouses.put(name, new Warehouse(name, address));
        inventory.put(name, new HashMap<>());
    }

    @Override
    public void addSupplier(String name, String address, String phone) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название поставщика не может быть пустым");
        }
        suppliers.put(name, new Supplier(name, address, phone));
    }

    @Override
    public void addProduct(String name, String unit, double price) {
        if (name == null || name.trim().isEmpty() || unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Название и единица измерения товара не могут быть пустыми");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена товара должна быть положительной");
        }
        products.put(name, new Product(name, unit, price));
    }

    @Override
    public Document createInvoice(String number, LocalDate date, String type,
                                  String supplier, String fromWarehouse,
                                  String toWarehouse, String basis) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер накладной не может быть пустым");
        }
        if (date == null) {
            throw new IllegalArgumentException("Дата накладной не может быть пустой");
        }
        if (type == null || !Arrays.asList("ПРИХОД", "РАСХОД", "ПЕРЕМЕЩЕНИЕ").contains(type)) {
            throw new IllegalArgumentException("Неверный тип накладной");
        }

        Invoice invoice = new Invoice(number, date, type);
        invoice.setSupplier(supplier);
        invoice.setFromWarehouse(fromWarehouse);
        invoice.setToWarehouse(toWarehouse);
        invoice.setBasis(basis);
        invoices.add(invoice);
        return invoice;
    }

    @Override
    public boolean processInvoice(Document document) {
        if (!(document instanceof Invoice invoice)) {
            return false;
        }

        try {
            validateInvoice(invoice);

            switch (invoice.getType()) {
                case "ПРИХОД":
                    return processIncoming(invoice);
                case "РАСХОД":
                    return processOutgoing(invoice);
                case "ПЕРЕМЕЩЕНИЕ":
                    return processTransfer(invoice);
                default:
                    return false;
            }
        } catch (Exception e) {
            System.out.println("Ошибка обработки накладной: " + e.getMessage());
            return false;
        }
    }

    private void validateInvoice(Invoice invoice) {
        if (invoice.getType().equals("ПРИХОД")) {
            if (invoice.getToWarehouse() == null) {
                throw new IllegalArgumentException("Для прихода необходимо указать склад назначения");
            }
            if (invoice.getSupplier() != null && !supplierExists(invoice.getSupplier())) {
                throw new IllegalArgumentException("Поставщик не найден: " + invoice.getSupplier());
            }
        } else if (invoice.getType().equals("РАСХОД")) {
            if (invoice.getFromWarehouse() == null) {
                throw new IllegalArgumentException("Для расхода необходимо указать склад отгрузки");
            }
        } else if (invoice.getType().equals("ПЕРЕМЕЩЕНИЕ")) {
            if (invoice.getFromWarehouse() == null || invoice.getToWarehouse() == null) {
                throw new IllegalArgumentException("Для перемещения необходимо указать склад отгрузки и назначения");
            }
        }

        Map<String, Integer> items = invoice.getItems();
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Накладная не содержит товаров");
        }

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();

            if (!productExists(product)) {
                throw new IllegalArgumentException("Товар не найден: " + product);
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Количество товара должно быть положительным: " + product);
            }
        }

        if (invoice.getFromWarehouse() != null && !warehouseExists(invoice.getFromWarehouse())) {
            throw new IllegalArgumentException("Склад отгрузки не найден: " + invoice.getFromWarehouse());
        }
        if (invoice.getToWarehouse() != null && !warehouseExists(invoice.getToWarehouse())) {
            throw new IllegalArgumentException("Склад назначения не найден: " + invoice.getToWarehouse());
        }
    }

    private boolean processIncoming(Invoice invoice) {
        String warehouse = invoice.getToWarehouse();
        Map<String, Integer> warehouseInventory = inventory.get(warehouse);

        for (Map.Entry<String, Integer> entry : invoice.getItems().entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            warehouseInventory.put(product, warehouseInventory.getOrDefault(product, 0) + quantity);
        }
        return true;
    }

    private boolean processOutgoing(Invoice invoice) {
        String warehouse = invoice.getFromWarehouse();
        Map<String, Integer> warehouseInventory = inventory.get(warehouse);

        for (Map.Entry<String, Integer> entry : invoice.getItems().entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            int current = warehouseInventory.getOrDefault(product, 0);

            if (current < quantity) {
                throw new IllegalArgumentException("Недостаточно товара: " + product +
                        " (доступно: " + current + ", требуется: " + quantity + ")");
            }

            int newQuantity = current - quantity;
            if (newQuantity == 0) {
                warehouseInventory.remove(product);
            } else {
                warehouseInventory.put(product, newQuantity);
            }
        }
        return true;
    }

    private boolean processTransfer(Invoice invoice) {
        String fromWarehouse = invoice.getFromWarehouse();
        String toWarehouse = invoice.getToWarehouse();

        if (fromWarehouse.equals(toWarehouse)) {
            throw new IllegalArgumentException("Перемещение на тот же склад невозможно");
        }

        Map<String, Integer> items = invoice.getItems();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();

            if (!hasEnoughProduct(fromWarehouse, product, quantity)) {
                throw new IllegalArgumentException("Недостаточно товара на складе " + fromWarehouse +
                        ": " + product + " (требуется: " + quantity + ")");
            }
        }

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();

            Map<String, Integer> fromInventory = inventory.get(fromWarehouse);
            Map<String, Integer> toInventory = inventory.get(toWarehouse);

            int fromCurrent = fromInventory.getOrDefault(product, 0);
            int fromNewQuantity = fromCurrent - quantity;
            if (fromNewQuantity == 0) {
                fromInventory.remove(product);
            } else {
                fromInventory.put(product, fromNewQuantity);
            }

            toInventory.put(product, toInventory.getOrDefault(product, 0) + quantity);
        }

        return true;
    }

    @Override
    public Map<String, Map<String, Integer>> getAllInventory() {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        inventory.forEach((warehouse, items) ->
                result.put(warehouse, new HashMap<>(items)));
        return result;
    }

    @Override
    public List<String> getAllSuppliers() {
        return new ArrayList<>(suppliers.keySet());
    }

    @Override
    public Map<String, Map<String, Integer>> searchProduct(String productName) {
        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> warehouseEntry : inventory.entrySet()) {
            String warehouse = warehouseEntry.getKey();
            Map<String, Integer> items = warehouseEntry.getValue();

            for (Map.Entry<String, Integer> productEntry : items.entrySet()) {
                String product = productEntry.getKey();
                int quantity = productEntry.getValue();

                if (product.toLowerCase().contains(productName.toLowerCase())) {
                    result.computeIfAbsent(warehouse, k -> new HashMap<>())
                            .put(product, quantity);
                }
            }
        }

        return result;
    }

    @Override
    public boolean warehouseExists(String name) {
        return warehouses.containsKey(name);
    }

    @Override
    public boolean supplierExists(String name) {
        return suppliers.containsKey(name);
    }

    @Override
    public boolean productExists(String name) {
        return products.containsKey(name);
    }

    @Override
    public boolean hasEnoughProduct(String warehouse, String product, int quantity) {
        if (!warehouseExists(warehouse) || !productExists(product)) {
            return false;
        }
        Map<String, Integer> warehouseInventory = inventory.get(warehouse);
        return warehouseInventory.getOrDefault(product, 0) >= quantity;
    }

    private static class Warehouse {
        private final String name;
        private final String address;

        public Warehouse(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }

    private static class Supplier {
        private final String name;
        private final String address;
        private final String phone;

        public Supplier(String name, String address, String phone) {
            this.name = name;
            this.address = address;
            this.phone = phone;
        }
    }

    private static class Product {
        private final String name;
        private final String unit;
        private final double price;

        public Product(String name, String unit, double price) {
            this.name = name;
            this.unit = unit;
            this.price = price;
        }
    }

    private class Invoice implements Document {
        private final String number;
        private final LocalDate date;
        private final String type;
        private String supplier;
        private String fromWarehouse;
        private String toWarehouse;
        private String basis;
        private final Map<String, Integer> items;

        public Invoice(String number, LocalDate date, String type) {
            this.number = number;
            this.date = date;
            this.type = type;
            this.items = new HashMap<>();
        }

        private void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        private void setFromWarehouse(String warehouse) {
            this.fromWarehouse = warehouse;
        }

        private void setToWarehouse(String warehouse) {
            this.toWarehouse = warehouse;
        }

        private void setBasis(String basis) {
            this.basis = basis;
        }

        @Override
        public String getSupplier() {
            return supplier;
        }

        @Override
        public String getFromWarehouse() {
            return fromWarehouse;
        }

        @Override
        public String getToWarehouse() {
            return toWarehouse;
        }

        @Override
        public String getNumber() {
            return number;
        }

        @Override
        public LocalDate getDate() {
            return date;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getBasis() {
            return basis;
        }

        @Override
        public Map<String, Integer> getItems() {
            return Collections.unmodifiableMap(items);
        }

        @Override
        public void addItem(String product, int quantity) {
            if (product == null || product.trim().isEmpty()) {
                throw new IllegalArgumentException("Название товара не может быть пустым");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Количество товара должно быть положительным");
            }
            items.put(product, items.getOrDefault(product, 0) + quantity);
        }

        @Override
        public double getTotalAmount() {
            double total = 0;
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                Product product = products.get(entry.getKey());
                if (product != null) {
                    total += product.price * entry.getValue();
                }
            }
            return total;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("НАКЛАДНАЯ №").append(number).append("\n");
            sb.append("Дата: ").append(date).append("\n");
            sb.append("Тип: ").append(type).append("\n");
            if (supplier != null) sb.append("Поставщик: ").append(supplier).append("\n");
            if (fromWarehouse != null) sb.append("Со склада: ").append(fromWarehouse).append("\n");
            if (toWarehouse != null) sb.append("На склад: ").append(toWarehouse).append("\n");
            if (basis != null) sb.append("Основание: ").append(basis).append("\n");

            sb.append("\nТОВАРЫ:\n");
            sb.append("Наименование\tЕд.изм.\tКол-во\tЦена\tСумма\n");
            sb.append("----------------------------------------\n");

            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                Product product = products.get(entry.getKey());
                if (product != null) {
                    double sum = product.price * entry.getValue();
                    sb.append(String.format("%s\t%s\t%d\t%.2f\t%.2f\n",
                            product.name, product.unit, entry.getValue(), product.price, sum));
                }
            }
            return sb.toString();
        }
    }
}