import java.util.Scanner;

public class Task7 {
    /*
    * Написать программу, которая проверяет, все ли значения элементов массива одинаковые.
    * Вывести: Yes – если все одинаковы и No – если имеется хоть одно различие.
    *  Массив задается и инициализируется в начале программы.
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = new int[5];

        System.out.println("Введите " + arr.length + " элементов массива:");
        fillArray(arr, scanner);

        boolean allEqual = areAllElementsEqual(arr);

        System.out.print("Массив: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        if (allEqual) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }

    public static boolean areAllElementsEqual(int[] arr){
        if (arr.length == 0) {
            return true;
        }

        int first = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != first) {
                return false;
            }
        }
        return true;
    }

    public static void fillArray(int[] arr, Scanner scanner) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }
    }
}