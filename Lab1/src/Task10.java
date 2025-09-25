package src;

public class Task10 {
    /*
    * Написать программу расчета идеального веса к росту.
    *  В константах хранятся рост (height) и вес (weight).
    * Вывести на консоль сообщение, сколько килограмм нужно набрать или сбросить (идеальный вес = рост - 110).
     */

    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations() {
        final double HEIGHT = 175.4;
        final double WEIGHT = 60;
        double idealWeight = HEIGHT - 110;

        if (WEIGHT > idealWeight){
            System.out.printf("Вам нужно сбросить %f кг", WEIGHT - idealWeight);
        } else if (WEIGHT < idealWeight) {
            System.out.printf("Вам нужно набрать %f кг", idealWeight - WEIGHT);
        } else {
            System.out.println("Ваш вес идеальный");
        }
    }
}
