import java.util.Arrays;
import java.util.Comparator;

public class Task3 {
    /*
    * •	В массиве хранится n явно заданных текстовых строк. Создать метод:
    выводящий содержимое массива в строку через пробел;
    сортирующий массив в обратном порядке (без учета регистра) от z до a;
    сортирующий массив по количеству слов в строке (слова разделены пробелами).
    Программа должна вывести строки в начальном и отсортированном порядке.

    * */

    public static void printArrayAsString(String[] array) {
        String result = String.join(" ", array);
        System.out.println(result);
    }

    public static void reverseSortIgnoreCase(String[] array) {
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareToIgnoreCase(s1);
            }
        });
    }

    public static void sortByWordCount(String[] array) {
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int wordCount1 = s1.split("\\s+").length;
                int wordCount2 = s2.split("\\s+").length;
                return Integer.compare(wordCount1, wordCount2);
            }
        });
    }

    public static void main(String[] args) {
        String[] strings = {
                "Java programming language",
                "Hello World",
                "Arrays and Strings",
                "Sorting algorithms",
                "Test string with multiple words for demonstration"
        };

        System.out.println("Начальный порядок строк:");
        for (int i = 0; i < strings.length; i++) {
            System.out.println((i + 1) + ". " + strings[i]);
        }

        System.out.println("\nСтроки в одну строку через пробел:");
        printArrayAsString(strings);

        String[] reverseSorted = Arrays.copyOf(strings, strings.length);
        String[] wordCountSorted = Arrays.copyOf(strings, strings.length);

        reverseSortIgnoreCase(reverseSorted);
        System.out.println("\nСортировка в обратном порядке (z-a):");
        for (int i = 0; i < reverseSorted.length; i++) {
            System.out.println((i + 1) + ". " + reverseSorted[i]);
        }

        sortByWordCount(wordCountSorted);
        System.out.println("\nСортировка по количеству слов:");
        for (int i = 0; i < wordCountSorted.length; i++) {
            int wordCount = wordCountSorted[i].split("\\s+").length;
            System.out.println((i + 1) + ". " + wordCountSorted[i] +
                    " (слов: " + wordCount + ")");
        }
    }
}