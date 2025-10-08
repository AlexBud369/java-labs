import java.util.Scanner;
public class Task7 {
    /*
    * С клавиатуры вводится целое положительное число любой разрядности.
    * Необходимо перевернуть это число, т. е. цифры должны располагаться
    * в обратном порядке (например, вводим число 1234 – в результате будет 4321). Не использовать строки и массивы.
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число для переворота: ");
        int number = scanner.nextInt();

        int reversedNumber = reverseNumber(number);
        System.out.println("Перевернутое число: " + reversedNumber);

        scanner.close();
    }


    public static int reverseNumber(int number) {
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }

        return reversed;
    }
}
