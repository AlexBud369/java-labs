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

        char inputValue = getInput("Введите символ");
        while (inputValue.length() != 1) {
            inputValue = getInput("Введите символ");
        }

        char ch = input.charAt(0);

        switch (ch) {
            case Character.isDigit(ch):
                System.out.println("цифра");
                break;
            case Character.isLetter(ch):
                switch (ch){
                    case ch >= 'а' && ch <= 'я' || ch >= 'А' && ch <= 'Я':
                        System.out.println("кириллица");
                        break;
                    default:
                        System.out.println("латиница");
                }
                break;
            default:
                System.out.println("не число и не буква");
        }
    }

    public static String getInput(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);

        String value = in.nextLine();
        in.close();

        return value;
    }
}