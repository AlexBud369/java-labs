/*
* Реализовать методы:
•	pushBack (добавление элемента в конец массива. Должна быть проверка, достаточно ли памяти!
*  Если памяти не достаточно увеличить емкость массива данных);
•	popFront (удаление первого элемента из массива);
•	pushFront (добавление нового элемента в начало массива);
•	insert (вставка нового элемента в массив по указанному индексу, с проверкой на выход за пределы массива);
•	removeAt (удаление одного элемента по указанному индексу. Должна быть проверка на допустимость индекса);
•	remove (удаление одного элемента, значение которого совпадает со значением переданного параметра);
•	removeAll (удаление всех элементов, значения которых совпадает со значением переданного параметра);
•	popBack (удаление последнего элемента из массива);
•	сlear (обнуление массива – всем элементам массива по индексам от 0 до size-1 присвоить значение null, полю size присвоить значение 0).

*
* */

public class Task03 {
    public static void main(String[] args) {

        MyArrayList<Integer> list = new MyArrayList<>();

        // Добавление элементов
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        System.out.println("После pushBack(1,2,3): [" + list + "]");

        list.pushFront(0);
        System.out.println("После pushFront(0): [" + list + "]");

        list.insert(2, 999);
        System.out.println("После insert(2, 999): [" + list + "]");

        // Удаление элементов
        list.removeAt(2);
        System.out.println("После removeAt(2): [" + list + "]");

        list.remove(Integer.valueOf(2));
        System.out.println("После remove(2): [" + list + "]");

        // Добавляем дубликаты и тестируем removeAll
        list.pushBack(5);
        list.pushBack(5);
        System.out.println("После добавления дубликатов: [" + list + "]");
        int removedCount = list.removeAll(5);
        System.out.println("После removeAll(5) (удалено " + removedCount + "): [" + list + "]");

        list.popBack();
        System.out.println("После popBack(): [" + list + "]");

        list.popFront();
        System.out.println("После popFront(): [" + list + "]");

        list.clear();
        System.out.println("После clear(): [" + list + "]");

        System.out.println("\nТестирование исключений:");
        try {
            list.popFront();
        } catch (IllegalStateException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }

        try {
            list.insert(5, 100);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }
    }
}