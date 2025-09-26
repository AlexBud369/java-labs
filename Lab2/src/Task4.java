import java.util.Scanner;

public class Task4 {

    /*Дана точка на плоскости заданная координатами x и y,
       определить и вывести в консоль, в какой четверти
       находится точка, в прямоугольной (декартовой) системе координат.
        Четверти обозначены римскими цифрами.
        */

    public static void main(String[] args) {

        double x =  getDigit("Введите координату x: ");

        double y =  getDigit("Введите координату y: );

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

    }

    public static double getDigit(String promt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(promt);
        double value = scanner.nextDouble();
        scanner.close();

        return value;
    }
}