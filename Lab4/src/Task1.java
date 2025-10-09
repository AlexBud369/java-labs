import java.util.Scanner;
public class Task1 {
    /*
    * Ввести с клавиатуры строку текста, а затем один символ.
    * Показать на консоль индексы и количество совпадений (ищем вхождения символа в строку).
    * В случае если совпадений не найдено, вывести соответствующий текст.
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку текста: ");
        String text = scanner.nextLine();

        System.out.print("Введите символ для поиска: ");
        char targetChar = scanner.next().charAt(0);

        System.out.print("Индексы совпадений: ");
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == targetChar) {
                System.out.print(i + " ");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("\nСовпадений не найдено");
        } else {
            System.out.println("\nКоличество совпадений: " + count);
        }

        scanner.close();
    }
}