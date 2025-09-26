import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
         /*Дана точка на плоскости заданная координатами x и y,
        определить и вывести в консоль, в какой четверти
        находится точка, в прямоугольной (декартовой) системе координат.
         Четверти обозначены римскими цифрами.
         */
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите координату x: ");
        double x = scanner.nextDouble();

        System.out.print("Введите координату y: ");
        double y = scanner.nextDouble();

        if (x == 0 && y == 0) {
            System.out.println("Точка находится в начале координат");
        } else if (x == 0) {
            System.out.println("Точка лежит на оси Y");
        } else if (y == 0) {
            System.out.println("Точка лежит на оси X");
        } else {
            if (x > 0 && y > 0) {
                System.out.println("Точка находится в четверти I");
            } else if (x < 0 && y > 0) {
                System.out.println("Точка находится в четверти II");
            } else if (x < 0 && y < 0) {
                System.out.println("Точка находится в четверти III");
            } else if (x > 0 && y < 0) {
                System.out.println("Точка находится в четверти IV");
            }
        }

        scanner.close();
    }
}