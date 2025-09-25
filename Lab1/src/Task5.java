package src;

public class Task5 {
    /*
    Подсчитать площадь и длину окружности для круга с радиусом R.
    Радиус должен быть задан константой в программе. Вывести результат на консоль.
    */
    public static void main(String[] args){
        GetOperations();
    }

    public static void GetOperations(){
        final int R = 5;
        final double PI = 3.14;
        double circleSquare = PI * Math.pow(R, 2);
        double circleLength = 2 * PI * R;

        System.out.println(String.format("Площадь круга %f, длина окружности %f", circleSquare, circleLength));
    }

}
