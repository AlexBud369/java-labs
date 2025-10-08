public class Task3 {
    /*
    * Самовлюблённое число или число Армстронга – натуральное число, которое равно сумме своих цифр,
    * возведенных в степень, равную количеству его цифр. Показать на экране все числа Армстронга в диапазоне от 10 до 1 000 000.
Например: 153 = 1^3 + 5^3 + 3^3

    * */
    public static void main(String[] args) {
        int count = 0;

        for (int number = 10; number <= 1_000_000; number++) {
            if (isArmstrongNumber(number)) {
                System.out.println(number);
                count++;
            }
        }

        System.out.println("Всего найдено чисел: " + count);
    }

    public static boolean isArmstrongNumber(int number) {
        int originalNumber = number;
        int sum = 0;
        int numberOfDigits = countDigits(number);

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, numberOfDigits);
            number /= 10;
        }

        return sum == originalNumber;
    }

    public static int countDigits(int number) {
        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }
}