import java.util.Scanner;

public class Task1{
    /*
    Написать программу, которая предлагает пользователю ввести c клавиатуры номер
     дня недели, и в ответ показывает название этого дня (например, 6 – это суббота). Решить с использованием switch.
     */

    public static void main(String[] args) {

        int dayNumber = GetNumber("Введите номер дня недели");

        while (dayNumber > 7 || dayNumber < 1) {
            dayNumber = GetNumber("Введите номер дня недели");
        }

        switch (dayNumber) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресение");
                break;
        }
    }

    public static int GetNumber(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        int value = in.nextInt();
        in.close();

        return value;
    }

}