package Task02;

import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {
    private final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook;

    public PhoneBookApp() {
        this.phoneBook = new PhoneBook(new FileContactStorage());
    }

    public void run() {
        System.out.println("=== ТЕЛЕФОННАЯ КНИГА ===");
        while (true) {
            showMenu();
            int choice = readChoice();
            switch (choice) {
                case 1 -> addContact();
                case 2 -> showAllNames();
                case 3 -> searchByName();
                case 4 -> searchByPhone();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Показать все имена");
        System.out.println("3. Найти по имени");
        System.out.println("4. Найти по номеру телефона");
        System.out.println("0. Выход");
        System.out.print("→ ");
    }

    private int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private void addContact() {
        System.out.println("\n=== ДОБАВЛЕНИЕ КОНТАКТА ===");
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();
        System.out.print("Прозвище (можно пусто): ");
        String nickname = scanner.nextLine();
        System.out.print("Год рождения (можно пусто): ");
        String yearStr = scanner.nextLine();
        Integer birthYear = yearStr.isEmpty() ? null : Integer.valueOf(yearStr);

        Contact contact = new Contact(firstName, lastName, nickname.isEmpty() ? null : nickname, birthYear);

        System.out.println("Добавьте телефоны (пустая строка — завершить):");
        while (true) {
            System.out.print("Номер: ");
            String phone = scanner.nextLine();
            if (phone.isBlank()) break;
            if (!Contact.isValidPhone(phone)) {
                System.out.println("Некорректный номер!");
                continue;
            }
            System.out.print("Тип (мобильный/домашний/рабочий/факс): ");
            String type = scanner.nextLine();
            if (type.isBlank()) type = "мобильный";
            contact.addPhone(phone, type);
        }

        System.out.println("Добавьте email (пустая строка — завершить):");
        while (true) {
            System.out.print("Email: ");
            String email =  scanner.nextLine();
            if (email.isBlank()) break;
            if (!Contact.isValidEmail(email)) {
                System.out.println("Некорректный email!");
                continue;
            }
            contact.addEmail(email);
        }

        if (phoneBook.addContact(contact)) {
            System.out.println("Контакт успешно добавлен!");
        }
    }

    private void showAllNames() {
        List<String> names = phoneBook.getAllNames();
        if (names.isEmpty()) {
            System.out.println("Книга пуста.");
        } else {
            System.out.println("\n=== ВСЕ КОНТАКТЫ ===");
            names.forEach(System.out::println);
        }
    }

    private void searchByName() {
        System.out.print("Введите имя, фамилию или прозвище: ");
        String query = scanner.nextLine();
        List<Contact> results = phoneBook.findByName(query);
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            System.out.println("\n=== РЕЗУЛЬТАТЫ ПОИСКА ===");
            results.forEach(System.out::println);
        }
    }

    private void searchByPhone() {
        System.out.print("Введите номер телефона: ");
        String phone = scanner.nextLine();
        Contact contact = phoneBook.findByPhone(phone);
        if (contact == null) {
            System.out.println("Контакт не найден.");
        } else {
            System.out.println("\n=== НАЙДЕННЫЙ КОНТАКТ ===");
            System.out.println(contact);
        }
    }
}