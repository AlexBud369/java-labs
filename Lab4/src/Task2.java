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

        // StringBuilder - для эффективного построения длинной строки
        StringBuilder sequence = new StringBuilder();
        int number = 1;  // Начинаем с числа 1

        // Пока длина строки меньше 1000, продолжаем добавлять числа
        while (sequence.length() < 1000) {
            String numberStr = String.valueOf(number);  // Преобразуем число в строку

            // Проверяем, не превысит ли добавление этого числа лимит в 1000 символов
            if (sequence.length() + numberStr.length() <= 1000) {
                sequence.append(numberStr);  // Добавляем число как строку
                number++;  // Переходим к следующему числу
            } else {
                // Если добавление целого числа превысит лимит, выходим из цикла
                break;
            }
        }

        // Получаем финальную строку (длиной <= 1000 символов)
        String result = sequence.toString();

        System.out.print("Введите позицию n (1-" + result.length() + "): ");
        int n = scanner.nextInt();

        // Проверяем корректность ввода
        if (n >= 1 && n <= result.length()) {
            // Получаем символ на нужной позиции (n-1 потому что индексы начинаются с 0)
            char digit = result.charAt(n - 1);
            System.out.println("Цифра на позиции " + n + ": " + digit);
        } else {
            System.out.println("Позиция должна быть от 1 до " + result.length());
        }

        scanner.close();
    }
}
