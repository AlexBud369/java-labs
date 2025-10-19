public class Task2 {
    /*
        * Написать и протестировать перегруженный метод, выводящий на экран:
    •	одномерный массив типа int;
    •	одномерный массив типа String;
    •	двухмерный массив типа int;
    •	двухмерный массив типа float

    * */
    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printArray(String[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("\"" + array[i] + "\"");
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printArray(int[][] array) {
        System.out.println("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("  [");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                if (j < array[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public static void printArray(float[][] array) {
        System.out.println("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("  [");
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%.2f", array[i][j]);
                if (j < array[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Тестовые данные
        int[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"apple", "banana", "cherry"};
        int[][] int2DArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
        float[][] float2DArray = {
                {1.1f, 2.2f},
                {3.3f, 4.4f}
            };

        System.out.println("Одномерный массив int:");
        printArray(intArray);

        System.out.println("\nОдномерный массив String:");
        printArray(stringArray);

        System.out.println("\nДвумерный массив int:");
        printArray(int2DArray);

        System.out.println("\nДвумерный массив float:");
        printArray(float2DArray);
    }
}