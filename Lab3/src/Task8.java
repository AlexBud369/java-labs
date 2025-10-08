import java.util.Scanner;
public class Task8 {
    /*
    *С клавиатуры вводится целое число любой разрядности. Программа должна определить
    *  и вывести на консоль количество цифр в этом числе, а так же сумму этих чисел.
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число: ");
        int number = Math.abs(scanner.nextInt());

        int digitCount = countDigits(number);
        int digitSum = sumDigits(number);

        System.out.println("Количество цифр: " + digitCount);
        System.out.println("Сумма цифр: " + digitSum);

        scanner.close();
    }

    public static int countDigits(int number) {
        if (number == 0) return 1;

        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    public static int sumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
