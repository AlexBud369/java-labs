import java.util.Random;

public class Task6 {
    /*
    * Заполнить массив на 30 элементов случайными числами от -70 до +50.
    * Найти минимальный элемент и вывести его на консоль. Найти максимальный элемент и вывести его на консоль.
     * */
    public static void main(String[] args) {

        int[] arr = createFilledArray();
        int maxItem = findMaxItem(arr);
        System.out.printf("Максимальный элемент %d", maxItem);
    }

    public static int findMaxItem(int[] arr){
        int maxItem = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > maxItem) {
                maxItem = arr[i];
            }
        }
        return maxItem;
    }

    public static int[] createFilledArray() {
        int[] arr = new int[30];
        int max = 50;
        int min = -70;
        fillArray(arr, max, min);

        return arr;
    }

    public static void fillArray(int[] arr, int max, int min) {
        Random rand = new Random();
        for (int i = 0; i < arr.length - 1; i++){
            arr[i] = rand.nextInt((max - min + 1) + min);
            System.out.print(arr[i] + " ");
        }
    }
}
