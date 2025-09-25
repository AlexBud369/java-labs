package src;

public class Task2 {

    /*
     * В переменной n хранится натуральное (целое) трехзначное число.
     * Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.
     */
    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations(){
        int n = 456;
        int firstDigit = n / 100;
        int secondDigit = (n % 100) / 10;
        int thirdDigit = n % 10;
        int sumOfN = firstDigit + secondDigit + thirdDigit;

        System.out.println(String.format("Сумма цифр числа %d равна %d", n, sumOfN));
    }

}
