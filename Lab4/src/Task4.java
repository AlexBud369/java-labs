import java.util.Scanner;
public class Task4 {
    /*
    * Пользователь вводит с клавиатуры любую строку.
    *  Поменять в исходной строке все большие буквы на маленькие, а маленькие – на большие.
    * Если в строке присутствуют цифры, заменить на символ подчеркивания и вывести результат на консоль.
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку: ");
        String input = scanner.nextLine();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isDigit(ch)) {
                result.append('_');
            } else if (Character.isUpperCase(ch)) {
                result.append(Character.toLowerCase(ch));
            } else if (Character.isLowerCase(ch)) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(ch);
            }
        }

        System.out.println("Результат: " + result.toString());
        scanner.close();
    }

}
