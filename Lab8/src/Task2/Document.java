package Task2;

import java.time.LocalDate;
import java.util.Map;

public interface Document {
    String getNumber();
    LocalDate getDate();
    String getType();
    String getBasis();
    double getTotalAmount();
    Map<String, Integer> getItems();
    String getSupplier();
    String getFromWarehouse();
    String getToWarehouse();
    void setSupplier(String supplier);
    void setFromWarehouse(String warehouse);
    void setToWarehouse(String warehouse);
    void setBasis(String basis);
    void addItem(String product, int quantity);
    String toString();
}