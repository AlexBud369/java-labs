package Task01;

import java.util.List;
import java.util.Scanner;

public class CalculatorApp {
    private final Scanner scanner = new Scanner(System.in);
    private final ICalculator calculator = new Calculator();
    private final IHistoryManager history = new HistoryManager();

    public void run() {
        System.out.println("=== КАЛЬКУЛЯТОР С ИСТОРИЕЙ ===");

        while (true) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> performCalculation();
                case 2 -> showHistory();
                case 3 -> clearHistory();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Выполнить операцию");
        System.out.println("2. Показать историю");
        System.out.println("3. Очистить историю");
        System.out.println("0. Выход");
        System.out.print("→ ");
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private void performCalculation() {
        System.out.print("Первое число: ");
        double a = readDouble();
        System.out.print("Второе число: ");
        double b = readDouble();
        System.out.print("Операция (+ - * / ^): ");
        String op = scanner.nextLine().trim();

        try {
            double result = calculator.calculate(a, b, op);
            System.out.printf("Результат: %.4f\n", result);

            Operation operation = new Operation(a, b, op, result);
            history.addOperation(operation);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private double readDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Некорректное число, использую 0");
            return 0;
        }
    }

    private void showHistory() {
        List<Operation> ops = history.getOperations();
        if (ops.isEmpty()) {
            System.out.println("История пуста");
            return;
        }
        System.out.println("\n=== ИСТОРИЯ ===");
        for (int i = 0; i < ops.size(); i++) {
            System.out.println((i + 1) + ". " + ops.get(i));
        }
    }

    private void clearHistory() {
        history.clearHistory();
        System.out.println("История очищена");
    }
}