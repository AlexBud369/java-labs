package src;

public class Task3 {
    /* В переменной n хранится вещественное число, с ненулевой дробной частью.
     Создайте программу, округляющую число n до ближайшего целого и выводящую
     результат округления на экран. */

    public static void main(String[] args){
        GetOperations();
    }

    public static void GetOperations(){
        double n = 4.3;
        long roundedNumber = Math.round(n);

        System.out.println(String.format("Число %f округлено до %d", n, roundedNumber));
    }
}
