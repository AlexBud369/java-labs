import java.util.Scanner;
public class Task5 {
    public static void main(String[] args)
    {
        /*Организовать ввод с клавиатуры
         даты рождения человека. Программа
          должна вывести знак зодиака и название
           года по китайскому календарю.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите день рождения (1-31): ");
        int day = scanner.nextInt();

        System.out.print("Введите месяц рождения (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Введите год рождения (например, 1990): ");
        int year = scanner.nextInt();
        if (year < 1900){
            System.out.println("ты не такой старый");
            return;
        }
        // Определение знака зодиака
        String zodiacSign = getZodiacSign(day, month);
        System.out.println("Знак зодиака: " + zodiacSign);

        // Определение года по китайскому календарю
        String chineseYear = getChineseYear(year);
        System.out.println("Год по китайскому календарю: " + chineseYear);

        scanner.close();
    }

    private static String getZodiacSign(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Овен";
        if (month == 4 || month == 5 && day <= 20) return "Телец";
        if (month == 5 || month == 6 && day <= 20) return "Близнецы";
        if (month == 6 || month == 7 && day <= 22) return "Рак";
        if (month == 7 || month == 8 && day <= 22) return "Лев";
        if (month == 8 || month == 9 && day <= 22) return "Дева";
        if (month == 9 || month == 10 && day <= 22) return "Весы";
        if (month == 10 || month == 11 && day <= 21) return "Скорпион";
        if (month == 11 || month == 12 && day <= 21) return "Стрелец";
        if (month == 12 || month == 1 && day <= 19) return "Козерог";
        if (month == 1 || month == 2 && day <= 18) return "Водолей";
        if (month == 2 || month == 3) return "Рыбы";
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