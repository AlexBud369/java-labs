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
    void addItem(String product, int quantity);
}