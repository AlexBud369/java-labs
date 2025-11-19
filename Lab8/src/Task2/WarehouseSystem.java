package Task2;

import java.time.LocalDate;
import java.util.*;

public class WarehouseSystem implements WarehouseManager {
    private Map<String, Warehouse> warehouses;
    private Map<String, Supplier> suppliers;
    private Map<String, Product> products;
    private List<Document> invoices;
    private Map<String, Map<String, Integer>> inventory;

    public WarehouseSystem() {
        this.warehouses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.products = new HashMap<>();
        this.invoices = new ArrayList<>();
        this.inventory = new HashMap<>();
    }

    @Override
    public void addWarehouse(String name, String address) {
        warehouses.put(name, new Warehouse(name, address));
        inventory.put(name, new HashMap<>());
    }

    @Override
    public void addSupplier(String name, String address, String phone) {
        suppliers.put(name, new Supplier(name, address, phone));
    }

    @Override
    public void addProduct(String name, String unit, double price) {
        products.put(name, new Product(name, unit, price));
    }

    @Override
    public Document createInvoice(String number, LocalDate date, String type) {
        Invoice invoice = new Invoice(number, date, type);
        invoices.add(invoice);
        return invoice;
    }

    @Override
    public boolean processInvoice(Document document) {
        if (!(document instanceof Invoice invoice)) {
            return false;
        }

        try {
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
                throw new IllegalArgumentException("Недостаточно товара: " + product);
            }
            warehouseInventory.put(product, current - quantity);
        }
        return true;
    }

    private boolean processTransfer(Invoice invoice) {
        if (!processOutgoing(invoice)) return false;

        // Создаем временную накладную для прихода
        Invoice tempInvoice = new Invoice(invoice.getNumber(), invoice.getDate(), "ПРИХОД");
        tempInvoice.setToWarehouse(invoice.getToWarehouse());
        tempInvoice.getItems().putAll(invoice.getItems());
        return processIncoming(tempInvoice);
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
    public Map<String, Integer> searchProduct(String productName) {
        Map<String, Integer> result = new HashMap<>();
        inventory.forEach((warehouse, items) -> {
            items.forEach((product, quantity) -> {
                if (product.toLowerCase().contains(productName.toLowerCase())) {
                    result.put(warehouse, result.getOrDefault(warehouse, 0) + quantity);
                }
            });
        });
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

    // Внутренние классы
    private static class Warehouse {
        private String name;
        private String address;

        public Warehouse(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }

    private static class Supplier {
        private String name;
        private String address;
        private String phone;

        public Supplier(String name, String address, String phone) {
            this.name = name;
            this.address = address;
            this.phone = phone;
        }
    }

    private static class Product {
        private String name;
        private String unit;
        private double price;

        public Product(String name, String unit, double price) {
            this.name = name;
            this.unit = unit;
            this.price = price;
        }
    }

    // Класс Invoice реализует интерфейс Document
    private class Invoice implements Document {
        private String number;
        private LocalDate date;
        private String type;
        private String supplier;
        private String fromWarehouse;
        private String toWarehouse;
        private String basis;
        private Map<String, Integer> items;

        public Invoice(String number, LocalDate date, String type) {
            this.number = number;
            this.date = date;
            this.type = type;
            this.items = new HashMap<>();
        }

        @Override
        public void setSupplier(String supplier) { this.supplier = supplier; }
        @Override
        public void setFromWarehouse(String warehouse) { this.fromWarehouse = warehouse; }
        @Override
        public void setToWarehouse(String warehouse) { this.toWarehouse = warehouse; }
        @Override
        public void setBasis(String basis) { this.basis = basis; }

        @Override
        public String getSupplier() { return supplier; }
        @Override
        public String getFromWarehouse() { return fromWarehouse; }
        @Override
        public String getToWarehouse() { return toWarehouse; }
        @Override
        public String getNumber() { return number; }
        @Override
        public LocalDate getDate() { return date; }
        @Override
        public String getType() { return type; }
        @Override
        public String getBasis() { return basis; }
        @Override
        public Map<String, Integer> getItems() { return new HashMap<>(items); }

        @Override
        public void addItem(String product, int quantity) {
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