import java.util.Scanner;

public class Task3 {
        /*Написать программу, которая предлагает пользователю выбрать
        животное из списка (1 – кошка, 2 – собака и т.д.), и в ответ
        показывает, какие звуки издает выбранное животное. В списке
        должно быть не менее 10 животных.
        */
    public static void main(String[] args) {

        int choice = getDigit();

        switch (choice) {
            case 1:
                System.out.println("Кошка говорит: Мяу!");
                break;
            case 2:
                System.out.println("Собака говорит: Гав!");
                break;
            case 3:
                System.out.println("Корова говорит: Муу!");
                break;
            case 4:
                System.out.println("Свинья говорит: Хрю!");
                break;
            case 5:
                System.out.println("Овца говорит: Бее!");
                break;
            case 6:
                System.out.println("Лошадь говорит: Иго-го!");
                break;
            case 7:
                System.out.println("Петух говорит: Кукареку!");
                break;
            case 8:
                System.out.println("Утка говорит: Кря-кря!");
                break;
            case 9:
                System.out.println("Козел говорит: Мее!");
                break;
            case 10:
                System.out.println("Осел говорит: Иа-иа!");
                break;
            default:
                System.out.println("Ошибка: введите число от 1 до 10");
        }


    }

    public static int getDigit() {
        Scanner scanner = new Scanner(System.in);
        outputText();
        int value = scanner.nextInt();
        scanner.close();
        return value;
    }

    public static void outputText()
    {
        System.out.println("Выберите животное:");
        System.out.println("1 - Кошка");
        System.out.println("2 - Собака");
        System.out.println("3 - Корова");
        System.out.println("4 - Свинья");
        System.out.println("5 - Овца");
        System.out.println("6 - Лошадь");
        System.out.println("7 - Петух");
        System.out.println("8 - Утка");
        System.out.println("9 - Козел");
        System.out.println("10 - Осел");
        System.out.print("Введите номер животного (1-10): ");
    }
}