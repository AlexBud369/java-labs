public class Task5 {
    /*
    * Вывести на консоль все восьмизначные числа, цифры в которых не повторяются.
    *  Эти числа должны делиться на 12345, без остатка. Показать общее количество найденных чисел.
    * */
    public static void main(String[] args) {
        int count = 0;

        for (int number = 10_000_000; number <= 99_999_999; number++) {
            if (number % 12345 == 0 && hasUniqueDigits(number)) {
                System.out.println(number);
                count++;
            }
        }

        System.out.println("Общее количество найденных чисел: " + count);
    }

    public static boolean hasUniqueDigits(int number) {
        boolean[] digits = new boolean[10];

        while (number > 0) {
            int digit = number % 10;
            if (digits[digit]) {
                return false;
            }
            digits[digit] = true;
            number /= 10;
        }

        return true;
    }

}
