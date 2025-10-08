public class Task1 {
    /*
    * Числа Фибоначчи – это последовательность чисел, в которой
    * два первых числа последовательности равны 0 и 1, а каждое
    * последующее число равно сумме двух предыдущих.
    * Показать на экране все числа Фибоначчи в диапазоне от 0 до 10 000 000
    * */

    public static void main(String[] args) {
        int maxDigit = 10_000_000;
        int a = 0;
        int b = 1;
        int next;

       while (b <= maxDigit) {
           System.out.println(b);
           next = a + b;
           a = b;
           b = next;

       }
    }
}