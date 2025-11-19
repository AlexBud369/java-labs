package Task2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WarehouseManager {
    // Управление складами
    void addWarehouse(String name, String address);
    void addSupplier(String name, String address, String phone);
    void addProduct(String name, String unit, double price);

    // Работа с накладными
    Document createInvoice(String number, LocalDate date, String type);
    boolean processInvoice(Document invoice);

    // Отчеты
    Map<String, Map<String, Integer>> getAllInventory();
    List<String> getAllSuppliers();
    Map<String, Integer> searchProduct(String productName);

    // Валидация
    boolean warehouseExists(String name);
    boolean supplierExists(String name);
    boolean productExists(String name);
}