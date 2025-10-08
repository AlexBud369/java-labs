import java.util.Scanner;
public class Task4 {
    /*
    * Напишите программу, которая будет проверять, является ли число, введенное
    *  с клавиатуры палиндромом (одинаково читающееся в обоих направлениях).
    * Например, 123454321 или 221122 – палиндром. Программа должна вывести YES,
    *  если число является палиндромом, и NO – в противоположном случае.
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число для проверки на палиндром: ");
        int number = scanner.nextInt();

        if (isPalindrome(number)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }

    public static boolean isPalindrome(int number) {
        int originalNumber = number;
        int reversedNumber = 0;

        while (number > 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }

        return originalNumber == reversedNumber;
    }
}