import java.util.Scanner;
public class Task5 {

    /*
    * Написать программу, проверяющую является ли одна строка анаграммой для другой
    * строки (строка может состоять из нескольких слов и символов пунктуации).
    * Пробелы и пунктуация должны игнорироваться при анализе.
    * Разница в больших и маленьких буквах должна игнорироваться. Обе строки должны вводиться с клавиатуры.
    * Программа должна выводить Yes, если строки являются анаграммой, и No – иначе.
    Пример анаграммы в стихах:
    Строка 1 «Аз есмь строка, живу я, мерой остр».
    Строка 2 «За семь морей ростка я вижу рост!»

    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первую строку: ");
        String str1 = scanner.nextLine();

        System.out.print("Введите вторую строку: ");
        String str2 = scanner.nextLine();

        if (areAnagrams(str1, str2)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        scanner.close();
    }

    public static boolean areAnagrams(String str1, String str2) {
        String cleanStr1 = cleanString(str1);
        String cleanStr2 = cleanString(str2);

        // Если длины разные - не анаграммы
        if (cleanStr1.length() != cleanStr2.length()) {
            return false;
        }

        int[] charCount = new int[26];

        for (char ch : cleanStr1.toCharArray()) {
            charCount[ch - 'a']++;
        }

        for (char ch : cleanStr2.toCharArray()) {
            charCount[ch - 'a']--;
        }

        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static String cleanString(String str) {
        return str.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
    }
}
