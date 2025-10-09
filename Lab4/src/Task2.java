import java.util.Scanner;

public class Task2 {
    /*
    * Написать программу, которая создаст строку, в которой находятся все целые числа,
    *  начиная с 1, выписаны в одну строку «123456789101112131415...». Строка должна
    *  быть длиной не более 1 000 символов. По числу n (введенного с клавиатуры),
    *  выведите цифру на n-й позиции (используется нумерация с 1).
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sequence = new StringBuilder();
        int number = 1;

        while (sequence.length() < 1000) {
            String numberStr = String.valueOf(number);

            if (sequence.length() + numberStr.length() <= 1000) {
                sequence.append(numberStr);
                number++;
            } else {
                break;
            }
        }

        String result = sequence.toString();

        System.out.print("Введите позицию n (1-" + result.length() + "): ");
        int n = scanner.nextInt();

        if (n >= 1 && n <= result.length()) {
            char digit = result.charAt(n - 1);
            System.out.println("Цифра на позиции " + n + ": " + digit);
        } else {
            System.out.println("Позиция должна быть от 1 до " + result.length());
        }

        scanner.close();
    }
}
