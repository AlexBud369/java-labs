package Task01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager implements IHistoryManager {
    private static final String FILE_NAME = "calculator_history.dat";
    private final List<Operation> operations = new ArrayList<>();

    public HistoryManager() {
        loadFromFile();
    }

    @Override
    public void addOperation(Operation operation) {
        operations.add(operation);
        saveToFile();
    }

    @Override
    public List<Operation> getOperations() {
        return new ArrayList<>(operations);
    }

    @Override
    public void clearHistory() {
        operations.clear();
        new File(FILE_NAME).delete();
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(operations);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения истории: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Operation> loaded = (List<Operation>) ois.readObject();
            operations.addAll(loaded);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки истории: " + e.getMessage());
        }
    }
}