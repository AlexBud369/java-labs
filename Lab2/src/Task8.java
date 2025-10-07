import java.util.Scanner;

public class Task8 {
    /*
    * Даны координаты начала и координаты конца отрезка.
    *  Если считать отрезок обозначением горки, то в одном случае он обозначает спуск, в другом – подъем.
    * Определить и вывести на экран – спуск это или подъем, ровная дорога или вообще отвесная.

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x1 = inputDouble("Введите координату x начала отрезка:", scanner);
        double y1 = inputDouble("Введите координату y начала отрезка:", scanner);
        double x2 = inputDouble("Введите координату x конца отрезка:", scanner);
        double y2 = inputDouble("Введите координату y конца отрезка:", scanner);

        if (x1 == x2 && y1 == y2) {
            System.out.println("точка");
        } else if (x1 == x2) {
            System.out.println("отвесная");
        } else if (y1 == y2) {
            System.out.println("ровная");
        } else if (y2 > y1) {
            System.out.println("подъем");
        } else {
            System.out.println("спуск");
        }

        scanner.close();
    }

    public static double inputDouble(String prompt, Scanner scanner) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Ошибка! Введите число.");
                scanner.next();
            }
        }
    }
}
