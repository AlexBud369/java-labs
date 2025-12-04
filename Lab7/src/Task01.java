
/*
* Создать два конструктора.
•	С параметром типа int. Задающего начальную емкость массива. Принимает один параметр (задает capacity),
* выделяет память под массив (size = 0).
•	По умолчанию (без параметров). Который выделяет память под массив на 10 элементов,
*  равных нулю (capacity = 10, size = 0). Переиспользовать конструктор с параметрами для уменьшения кода.


* */
public class Task01 {
    public static void main(String[] args) {

        // Конструктор по умолчанию
        MyArrayList<String> defaultList = new MyArrayList<>();
        System.out.println("Конструктор по умолчанию: size = " + defaultList.getSize());

        // Конструктор с параметром
        MyArrayList<Integer> customList = new MyArrayList<>(5);
        System.out.println("Конструктор с емкостью 5: size = " + customList.getSize());

        // Демонстрация с разными типами
        MyArrayList<Double> doubleList = new MyArrayList<>(3);
        System.out.println("Double список создан: size = " + doubleList.getSize());

        try {
            MyArrayList<Object> invalidList = new MyArrayList<>(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }

        System.out.println("Все конструкторы работают корректно!");
    }
}