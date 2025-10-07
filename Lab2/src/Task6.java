import java.util.Scanner;

public class Task6 {
    /*
    * Пользователь вводит с клавиатуры букву.
    * Программа должна определить, в какой раскладке введена буква – в латинской или кириллице.
    * Вывести в консоль: «латиница», если буква введена латиницей или «кириллице»,
    * если буква относится к кириллическом алфавиту. Если введена цифра, а не буква, вывести «цифра».
    * Если символ не относится ни к буквам, ни к цифрам, вывести «невозможно определить».
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String inputValue;
        do {
            System.out.println("Введите символ:");
            inputValue = scanner.nextLine();
        } while (inputValue.length() != 1);

        scanner.close();

        char ch = inputValue.charAt(0);

        if (Character.isDigit(ch)) {
            System.out.println("цифра");
        } else if (Character.isLetter(ch)) {
            if ((ch >= 'а' && ch <= 'я') || (ch >= 'А' && ch <= 'Я') || ch == 'ё' || ch == 'Ё') {
                System.out.println("кириллица");
            } else {
                System.out.println("латиница");
            }
        } else {
            System.out.println("невозможно определить");
        }
    }
}