import java.util.Scanner;
public class Task5 {

    /*Организовать ввод с клавиатуры
        даты рождения человека. Программа
         должна вывести знак зодиака и название
          года по китайскому календарю.
        */

    public static void main(String[] args)
    {

        int day = getDigit("Введите день рождения (1-31): ");

        int month = getDigit("Введите месяц рождения (1-12): ");

        int year = getDigit("Введите год рождения (например, 1970): ");

        while (year < 1900) {
            year = getDigit("Недопустимый год. Введите заново: ");
        }

        String zodiacSign = getZodiacSign(day, month);
        System.out.println("Знак зодиака: " + zodiacSign);

        String chineseYear = getChineseYear(year);
        System.out.println("Год по китайскому календарю: " + chineseYear);

    }

    public static int getDigit(String promt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(promt);
        int value = scanner.nextInt();
        scanner.close();

        return value;
    }

    private static String getZodiacSign(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
            return "Овен";
        }
        if (month == 4 || month == 5 && day <= 20) {
            return "Телец";
        }
        if (month == 5 || month == 6 && day <= 20) {
            return "Близнецы";
        }
        if (month == 6 || month == 7 && day <= 22) {
            return "Рак";
        }
        if (month == 7 || month == 8 && day <= 22) {
            return "Лев";
        }
        if (month == 8 || month == 9 && day <= 22) {
            return "Дева";
        }
        if (month == 9 || month == 10 && day <= 22) {
            return "Весы";
        }
        if (month == 10 || month == 11 && day <= 21) {
            return "Скорпион";
        }
        if (month == 11 || month == 12 && day <= 21) {
            return "Стрелец";
        }
        if (month == 12 || month == 1 && day <= 19) {
            return "Козерог";
        }
        if (month == 1 || month == 2 && day <= 18) {
            return "Водолей";
        }
        if (month == 2 || month == 3) {
            return "Рыбы";
        }
        return "Некорректная дата";
    }

    private static String getChineseYear(int year) {
        String[] animals = {"Обезьяна", "Петух", "Собака", "Свинья", "Крыса", "Бык", "Тигр", "Кролик", "Дракон", "Змея", "Лошадь", "Коза"};
        String[] elements = {"Деревянная", "Огненная", "Земляная", "Металлическая", "Водяная"};

        int cycleIndex = (year - 1900) % 12;
        int elementIndex = (year - 1900) % 5;

        return elements[elementIndex] + " " + animals[cycleIndex];
    }

}