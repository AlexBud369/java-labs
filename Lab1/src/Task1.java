public class Task1 {

    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations(){
        int x = 9;
        int y = 3;

        int integerDivision = x/y;
        int remainderDivision = x%y;
        double squareRoot = Math.sqrt(x);

        System.out.println(String.format("Целочисленное деление %d, остаток от деления %d, квадратный корень %f", integerDivision, remainderDivision, squareRoot));
    }


}