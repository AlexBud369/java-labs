
/*
* Реализовать методы:
•	геттеры для size. Сеттера для size не должно быть!
•	переопределить метод toString и реализовать строковое представление элементов массива через пробел.
•	ensureCapacity – закрытый метод! проверяет, достаточно ли резерва памяти для хранения указанного в
* параметре количества элементов. Если значение параметра меньше текущего capacity, то ничего не происходит.
* Если значение параметра больше текущего capacity, то массив пересоздается,
* памяти выделяется в 1,5 раза + 1 элемент больше. Существующие элементы переносятся в новый массив.
* Существующие элементы не должны быть потеряны.

*
* */

public class Task02 {
    public static void main(String[] args) {

        MyArrayList<String> list = new MyArrayList<>(2);

        // Демонстрация геттера size
        System.out.println("Начальный размер: " + list.getSize());

        // Демонстрация toString
        System.out.println("Пустой массив: '" + list + "'");

        // Демонстрация ensureCapacity через добавление элементов
        System.out.println("\nДемонстрация ensureCapacity:");
        list.pushBack("Первый");
        System.out.println("После добавления 1 элемента: '" + list + "'");

        list.pushBack("Второй");
        System.out.println("После добавления 2 элемента: '" + list + "'");

        // Здесь сработает ensureCapacity
        list.pushBack("Третий");
        System.out.println("После добавления 3 элемента: '" + list + "'");
        System.out.println("Текущий размер: " + list.getSize());

        System.out.println("Все базовые методы работают корректно!");
    }
}