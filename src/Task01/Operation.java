package Task01;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operation implements Serializable {
    private final double operand1;
    private final double operand2;
    private final String operator;
    private final double result;
    private final LocalDateTime timestamp;

    public Operation(double operand1, double operand2, String operator, double result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%s: %.4f %s %.4f = %.4f",
                timestamp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                operand1, operator, operand2, result);
    }
}