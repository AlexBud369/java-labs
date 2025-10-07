import java.util.Scanner;

public class Task7 {
    /*
    * Даны два числа x и y. Программа должна вывести в консоль YES, – если оба числа четные,
    * либо оба числа нечетные; иначе программа ничего не выводит.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("введите x:");
        int x = scanner.nextInt();

        System.out.println("введите y:");
        int y = scanner.nextInt();

        scanner.close();

        if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
            System.out.println("YES");
        }
    }
}
