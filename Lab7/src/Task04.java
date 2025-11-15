/*
* Реализовать методы:
•	reverse (изменение порядка следования элементов в массиве на противоположный);
•	shuffle (случайное перемешивание элементов массива);
•	equals (в качестве параметра передается ссылка на другой объект класса MyArrayList.
* Метод сравнивает массивы не только по количеству элементов, но и по их содержимому);
•	getElementAt (возврат копии элемента массива по указанному индексу, с проверкой на выход за пределы массива);
•	переопределить метод clone – метод создает точную копию MyArrayList и возвращает ссылку на эту копию.

* */

public class Task04 {
    public static void main(String[] args) {

        MyArrayList<String> list = new MyArrayList<>();
        list.pushBack("A");
        list.pushBack("B");
        list.pushBack("C");
        list.pushBack("D");
        list.pushBack("E");

        System.out.println("Исходный массив: [" + list + "]");

        // Реверс
        list.reverse();
        System.out.println("После reverse(): [" + list + "]");

        // Перемешивание
        list.shuffle();
        System.out.println("После shuffle(): [" + list + "]");

        // Сравнение
        MyArrayList<String> copy = list.clone();
        System.out.println("Копия: [" + copy + "]");
        System.out.println("list.equals(copy): " + list.equals(copy));

        // Получение элемента
        System.out.println("Элемент по индексу 2: " + list.getElementAt(2));

        // Клонирование и проверка независимости
        MyArrayList<String> cloned = list.clone();
        cloned.pushBack("NEW_ELEMENT");
        System.out.println("После изменения клона:");
        System.out.println("Оригинал: [" + list + "]");
        System.out.println("Клон: [" + cloned + "]");
        System.out.println("list.equals(cloned): " + list.equals(cloned));

        // Обработка исключений
        System.out.println("\nТестирование исключений:");
        try {
            list.getElementAt(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }

        System.out.println("Все дополнительные методы работают корректно!");
    }
}