import java.util.Scanner;

public class Task2 {
    /*
    * С клавиатуры вводится время (количество часов от 0 до 24) – программа выводит приветствие,
    *  соответствующее введенному времени (например, ввели 15 часов – выводится приветствие «Добрый день»).
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите время (0-24 часа): ");
        int hour = scanner.nextInt();

        if (hour >= 0 && hour <= 24) {
            String greeting = getGreeting(hour);
            System.out.println(greeting);
        } else {
            System.out.println("Некорректное время!");
        }

        scanner.close();
    }

    public static String getGreeting(int hour) {
        if (hour >= 6 && hour < 12) {
            return "Доброе утро!";
        } else if (hour >= 12 && hour < 18) {
            return "Добрый день!";
        } else if (hour >= 18 && hour < 23) {
            return "Добрый вечер!";
        } else {
            return "Доброй ночи!";
        }
    }
}