import java.util.Scanner;
public class Task10 {
    /*
    * Программа запрашивает шестизначное число. После ввода определяет,
    * будет ли являться «счастливым» билет с таким номером (сумма первых трех цифр совпадает с суммой трех последних).
     * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ticketNumber = inputTicketNumber(scanner);
        checkLuckyTicket(ticketNumber);

        scanner.close();
    }

    public static int inputTicketNumber(Scanner scanner) {
        while (true) {
            System.out.println("Введите шестизначный номер билета:");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= 100000 && number <= 999999) {
                    return number;
                } else {
                    System.out.println("Ошибка! Номер должен быть шестизначным числом.");
                }
            } else {
                System.out.println("Ошибка! Введите целое число.");
                scanner.next();
            }
        }
    }

    public static void checkLuckyTicket(int ticketNumber) {
        int digit1 = ticketNumber / 100000;
        int digit2 = (ticketNumber / 10000) % 10;
        int digit3 = (ticketNumber / 1000) % 10;
        int digit4 = (ticketNumber / 100) % 10;
        int digit5 = (ticketNumber / 10) % 10;
        int digit6 = ticketNumber % 10;

        int sumFirstThree = digit1 + digit2 + digit3;
        int sumLastThree = digit4 + digit5 + digit6;

        if (sumFirstThree == sumLastThree) {
            System.out.println("Да");
        } else {
            System.out.println("Нет");
        }
    }
}
