package Task01;

public class Calculator implements ICalculator {
    @Override
    public double calculate(double a, double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Деление на ноль запрещено");
                yield a / b;
            }
            case "^" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Неизвестная операция: " + operator);
        };
    }

    @Override
    public String[] getSupportedOperations() {
        return new String[]{"+", "-", "*", "/", "^"};
    }
}