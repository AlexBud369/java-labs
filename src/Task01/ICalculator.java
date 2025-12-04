package Task01;

public interface ICalculator {
    double calculate(double a, double b, String operator)
            throws ArithmeticException, IllegalArgumentException;
    String[] getSupportedOperations();
}