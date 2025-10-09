import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Task8 {
    /*
    * Заполнить массив размерности n случайными цифрами от –2 до n.
    * Если в массиве есть хотя бы одно отрицательное значение меньше -1,
    * заменить все отрицательные значение в массиве на квадрат (в степени 2) этих значений.
    * Вывести исходный и результирующий массив на консоль.
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размерность массива n: ");
        int n = scanner.nextInt();


        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n + 3) - 2;
        }

        System.out.println("Исходный массив: " + Arrays.toString(array));

        boolean hasNegativeLessThanMinusOne = false;
        for (int num : array) {
            if (num < -1) {
                hasNegativeLessThanMinusOne = true;
                break;
            }
        }

        int[] resultArray = Arrays.copyOf(array, array.length);

        if (hasNegativeLessThanMinusOne) {
            for (int i = 0; i < resultArray.length; i++) {
                if (resultArray[i] < 0) {
                    resultArray[i] = resultArray[i] * resultArray[i];
                }
            }
        }

        System.out.println("Результирующий массив: " + Arrays.toString(resultArray));

        scanner.close();
    }
}
