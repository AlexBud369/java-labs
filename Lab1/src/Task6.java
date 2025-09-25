package src;

public class Task6 {
    /*
    *Есть прямоугольник, у которого известна ширина w и высота h.
    *Найти и вывести на консоль периметр и площадь заданного прямоугольника.
    *Высота и ширина прямоугольника должна задаваться константными переменными в коде программы.
     */

    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations(){
        final int width = 5;
        final int height = 7;

        int rectanglePerimeter = 2 * (5 + 7);
        int rectangleSquare = 5 * 7;

        System.out.println(String.format("Периметр %d, площадь %d", rectanglePerimeter, rectangleSquare));

    }

}
