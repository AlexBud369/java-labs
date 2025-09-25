package src;

import java.util.Scanner;

public class Task8 {
    /*
    * Зная скорость распространения звука в воздушной среде, можно вычислить
    * расстояние до места удара молнии по времени между вспышкой и раскатом грома.
    * Зная время в секундах между вспышкой и раскатом грома (константа в программе),
    * вычислите расстояния до места удара молнии и выведите его на экран
     */
    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations() {
        final double SOUND_SPEED = 343.0;
        double time = GetInputValue("Введите время в секундах");

        while (time < 0){
            time = GetInputValue("Введите время в секундах");
        }

        double distance = SOUND_SPEED * time;

        System.out.printf("""
                Время между вспышкой и громом: %.1f секунд%n
                Расстояние до молнии: %.1f метров%n
                Расстояние до молнии: %.2f километров%n
                """, time, distance, distance / 1000);
    }

    public static double GetInputValue(String promt){
        Scanner in = new Scanner(System.in);

        System.out.println(promt);
        double value = in.nextDouble();
        in.close();

        return value;
    }
}
