package Task2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WarehouseManager {
    void addWarehouse(String name, String address);
    void addSupplier(String name, String address, String phone);
    void addProduct(String name, String unit, double price);

    Document createInvoice(String number, LocalDate date, String type,
                           String supplier, String fromWarehouse,
                           String toWarehouse, String basis);
    boolean processInvoice(Document invoice);

    Map<String, Map<String, Integer>> getAllInventory();
    List<String> getAllSuppliers();
    Map<String, Map<String, Integer>> searchProduct(String productName);

    boolean warehouseExists(String name);
    boolean supplierExists(String name);
    boolean productExists(String name);
    boolean hasEnoughProduct(String warehouse, String product, int quantity);
}