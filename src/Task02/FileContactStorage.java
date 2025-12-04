package Task02;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileContactStorage implements IContactStorage {
    private static final String FILE_NAME = "phonebook.dat";

    @Override
    public void saveContacts(Set<Contact> contacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(new HashSet<>(contacts));
        } catch (IOException e) {
            System.err.println("Ошибка сохранения контактов: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Contact> loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new HashSet<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Set<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки контактов: " + e.getMessage());
            return new HashSet<>();
        }
    }
}