import java.util.Arrays;
import java.util.Random;
import java.util.Objects;

public class MyArrayList<T> {
    private T[] data;
    private int size;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    // Конструктор с параметром
    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Емкость должна быть положительной: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    // Конструктор по умолчанию
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // Геттер для size
    public int getSize() {
        return size;
    }

    // Проверка и увеличение емкости
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            int newCapacity = Math.max((int)(capacity * GROWTH_FACTOR) + 1, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
            capacity = newCapacity;
        }
    }

    // Добавление в конец
    public void pushBack(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    // Удаление первого элемента
    public void popFront() {
        if (size == 0) throw new IllegalStateException("Массив пуст");
        removeAt(0);
    }

    // Добавление в начало
    public void pushFront(T element) {
        insert(0, element);
    }

    // Вставка по индексу
    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    // Удаление по индексу
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
    }

    // Удаление первого совпадения
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, data[i])) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // Удаление всех совпадений
    public int removeAll(T element) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, data[i])) {
                removeAt(i);
                i--;
                count++;
            }
        }
        return count;
    }

    // Удаление последнего
    public void popBack() {
        if (size == 0) throw new IllegalStateException("Массив пуст");
        data[--size] = null;
    }

    // Очистка массива
    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    // Реверс массива
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            T temp = data[i];
            data[i] = data[size - 1 - i];
            data[size - 1 - i] = temp;
        }
    }

    // Перемешивание элементов
    public void shuffle() {
        Random rnd = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            T temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    // Сравнение с другим MyArrayList
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MyArrayList<?> other = (MyArrayList<?>) obj;
        if (size != other.size) return false;

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(data[i], other.data[i])) {
                return false;
            }
        }
        return true;
    }

    // Получение элемента по индексу
    public T getElementAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        return data[index];
    }

    // Создание копии
    @Override
    public MyArrayList<T> clone() {
        MyArrayList<T> copy = new MyArrayList<>(capacity);
        System.arraycopy(data, 0, copy.data, 0, size);
        copy.size = size;
        return copy;
    }

    // Строковое представление
    @Override
    public String toString() {
        if (size == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(" ");
        }
        return sb.toString();
    }
}