import java.util.Scanner;
import java.util.Random;

public class Task9 {
    /*
    * Создать квадратный массив размерности n заполненный случайными числами,
    * вывести массив на экран в виде таблицы, найти наименьший и наибольший элемент
    * массива и вывести их на экран (если найдено несколько одинаковых элементов – вывести
    * индексы строка и столбца, где есть повторения). Размерность массива должна задаваться с клавиатуры.
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размерность квадратного массива n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(101);
            }
        }

        System.out.println("Массив " + n + "x" + n + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }

        int min = matrix[0][0];
        int max = matrix[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        System.out.println("\nНаименьший элемент: " + min);
        System.out.println("Наибольший элемент: " + max);

        System.out.println("\nИндексы наименьшего элемента (" + min + "):");
        boolean foundMin = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == min) {
                    System.out.println("Строка: " + (i + 1) + ", Столбец: " + (j + 1));
                    foundMin = true;
                }
            }
        }
        if (!foundMin) {
            System.out.println("Не найдено");
        }

        System.out.println("\nИндексы наибольшего элемента (" + max + "):");
        boolean foundMax = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == max) {
                    System.out.println("Строка: " + (i + 1) + ", Столбец: " + (j + 1));
                    foundMax = true;
                }
            }
        }
        if (!foundMax) {
            System.out.println("Не найдено");
        }

        scanner.close();
    }
}
