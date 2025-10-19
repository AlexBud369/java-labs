import java.util.Scanner;
import java.util.Stack;

public class Task4 {
    /*
    * •	Напишите метод, проверяющую правильность расстановки скобок в строке, введенной с клавиатуры. При правильной расстановке выполняются условия: количество открывающих и закрывающих скобок равно;
•	внутри любой пары открывающая–соответствующая закрывающая скобка, скобки расставлены правильно. В строке могут присутствовать как круглые, так и квадратные скобки (и др. символы). Каждой открывающей скобке соответствует закрывающая того же типа (круглой – круглая, квадратной – квадратная).

    Пример неправильной расстановки: ( [ a) b]
    Пример правильных входных данных: (a[b](f[(g)(g)]))

    Программа должна вывести результат в виде сообщения, примеры:
    •	Правильная строка
    •	Ошибка отсутствие (
    •	Ошибка отсутствие )
    •	Ошибка отсутствие [
    •	Ошибка отсутствие ]

    * */

    public static void checkBrackets(String input) {
        Stack<Character> stack = new Stack<>();
        boolean hasError = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Ошибка: лишняя закрывающая скобка '" + ch + "'");
                    hasError = true;
                    continue;
                }

                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    System.out.println("Ошибка: несоответствие скобок '" + top + "' и '" + ch + "'");
                    hasError = true;
                }
            }
        }

        if (!stack.isEmpty()) {
            hasError = true;
            while (!stack.isEmpty()) {
                char missing = getClosingBracket(stack.pop());
                System.out.println("Ошибка: отсутствие '" + missing + "'");
            }
        }

        if (!hasError) {
            System.out.println("Правильная строка");
        }
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    private static char getClosingBracket(char open) {
        switch (open) {
            case '(': return ')';
            case '[': return ']';
            case '{': return '}';
            default: return ' ';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку для проверки скобок:");
        String input = scanner.nextLine();

        System.out.println("\nРезультат проверки:");
        checkBrackets(input);

        System.out.println("\n--- Тестовые примеры ---");

        String[] testCases = {
                "(a[b](f[(g)(g)]))",
                "([a)b]",
                "((())",
                "())",
                "{[()]}",
                "{[(])}"
        };

        for (String test : testCases) {
            System.out.println("\nСтрока: " + test);
            checkBrackets(test);
        }

        scanner.close();
    }
}