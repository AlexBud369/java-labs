import java.util.Scanner;

public class Task9 {
    /*
    * Найти корни квадратного уравнения и вывести их на экран, если они есть.
    *  Если корней нет, то вывести сообщение об этом. Конкретное квадратное
    *  уравнение определяется коэффициентами a, b, c, которые вводит пользователь с клавиатуры.
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Решение квадратного уравнения ax^2 + bx + c = 0");
        double a = inputCoefficient("a", scanner);

        if (a == 0) {
            System.out.println("Ошибка! Коэффициент a не может быть равен нулю для квадратного уравнения.");
            return;
        }

        double b = inputCoefficient("b", scanner);
        double c = inputCoefficient("c", scanner);

        solveQuadraticEquation(a, b, c);

        scanner.close();
    }

    public static double inputCoefficient(String coefficientName, Scanner scanner) {
        while (true) {
            System.out.printf("Введите коэффициент %s: ", coefficientName);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Ошибка! Введите число.");
                scanner.next();
            }
        }
    }

    public static void solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.printf("Уравнение имеет два корня: x1 = %.2f, x2 = %.2f\n", x1, x2);
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.printf("Уравнение имеет один корень: x = %.2f\n", x);
        } else {
            System.out.println("Уравнение не имеет действительных корней");
        }
    }
}
