public class Task1 {

    /*
    *  В переменных х и y хранятся два натуральных числа. Создайте программу, выводящую на консоль:
    *   результат целочисленного деления x на y;
    *   остаток от деления x на y;
    *   квадратный корень x.
     */
    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations(){
        int x = 9;
        int y = 3;

        int integerDivision = x / y;
        int remainderDivision = x % y;
        double squareRoot = Math.sqrt(x);

        System.out.println(String.format("""
                Целочисленное деление %d,
                остаток от деления %d,
                квадратный корень %f""",
                integerDivision, remainderDivision, squareRoot));
    }
}