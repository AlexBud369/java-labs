import java.util.Scanner;
public class Task3 {
    /*
    * Подсчитать среднюю длину слова, во введенном с клавиатуры предложения.
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите предложение: ");
        String sentence = scanner.nextLine();

        // Разбиваем на слова (учитываем знаки препинания)
        String[] words = sentence.split("[\\s\\p{Punct}]+");

        int totalLength = 0;
        int wordCount = 0;

        // Считаем общую длину слов
        for (String word : words) {
            if (!word.isEmpty()) {
                totalLength += word.length();
                wordCount++;
            }
        }

        if (wordCount > 0) {
            double averageLength = (double) totalLength / wordCount;
            System.out.printf("Средняя длина слова: %.2f\n", averageLength);
        } else {
            System.out.println("В предложении нет слов");
        }

        scanner.close();
    }
}
