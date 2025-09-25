package src;
import java.util.Scanner;

public class Task7 {
    /*
    * Разработать программу, которая позволит при известном годовом проценте
    * вычислить сумму вклада в банке через два года, если задана исходная величина вклада.
     */

    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations() {

        int minValueOfContribution = 100;
        int yearsCount = 2;
        double yearPercent = 5.0;
        double contribution = GetInputValue("Введите значение вклада (больше 100) ");

        while (contribution < minValueOfContribution){
            contribution = GetInputValue("Введите значение вклада (больше 100) ");
        }

        double contributionSum = contribution * Math.pow((1 + yearPercent / 100), yearsCount);

        System.out.printf("Сумма вклада %f с годовым процентом %f через %d года", contributionSum, yearPercent, yearsCount);

    }

    public static double GetInputValue(String promt){
        Scanner in = new Scanner(System.in);

        System.out.println(promt);
        double value = in.nextDouble();

        in.close();

        return value;
    }

}
