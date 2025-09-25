package src;

import java.util.Scanner;

public class Task9 {
    /*
     * Проверить, имеет ли число вещественную часть.
     *  Например, числа 3.14 и 2.5 – имеют вещественную часть, а числа 5.0 и 10.0 – нет.
     */

    public static void main(String[] args){
        GetOperations();
    }

    public static void GetOperations() {
        double num = GetNumberValue("Введите число");

        if (num % 1 == 0) {
            System.out.println(num + " - целое число (без вещественной части)");
        } else {
            System.out.println(num + " - имеет вещественную часть");
        }
    }

    public static double GetNumberValue(String promt) {
        Scanner in = new Scanner(System.in);

        System.out.println(promt);
        double value = in.nextDouble();
        in.close();

        return value;
    }

}
