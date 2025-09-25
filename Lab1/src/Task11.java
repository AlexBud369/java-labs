package src;

public class Task11 {
    /*
    * У Деда Мороза есть часы, которые в секундах показывают,
    * сколько осталось до каждого Нового года.
    * Так как Дед Мороз – человек уже в возрасте, то некоторые математические операции
    * он быстро выполнять не в состоянии. Помогите Деду Морозу определить, сколько полных
    * дней, часов, минут и секунд осталось до следующего Нового года, если известно сколько осталось секунд.
    * Т. е. разложите время в секундах на полное количество дней, часов, минут и секунд. Выведите результат на консоль.
    * Пример, как должен выглядеть вывод результата: 10 дней, 14 часов, 5 минут и 33 секунды
     */

    public static void main(String[] args) {
        GetOperations();
    }

    public static void GetOperations() {
        long secondsToNewYear = 900061;

        long days = secondsToNewYear / 86400;
        long hours = (secondsToNewYear % 86400) / 3600;
        long minutes = (secondsToNewYear % 3600) / 60;
        long seconds = secondsToNewYear % 60;

        System.out.printf("%d дней, %d часов, %d минут и %d секунд%n",
                days, hours, minutes, seconds);
    }
}
