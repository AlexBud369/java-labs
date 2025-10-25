package Task2;/*
* Описать базовый класс MainString (Строка). Обязательные поля класса:
- массив символов;
 - значение типа int хранит длину строки в символах.
Реализовать обязательные методы следующего назначения:
- конструктор без параметров;
- конструктор, принимающий в качестве параметра строковый литерал;
- конструктор, принимающий в качестве параметра символ;
- метод получения длины строки;
- метод очистки строки (делает строку пустой);
- метод поиска символа в строке.
*
* */

public class Task2 {
    private char[] chars;
    private int length;

    public Task2() {
        this.chars = new char[0];
        this.length = 0;
    }

    public Task2(String str) {
        this.chars = str.toCharArray();
        this.length = str.length();
    }

    public Task2(char c) {
        this.chars = new char[]{c};
        this.length = 1;
    }

    public int getLength() {
        return length;
    }

    public void clear() {
        this.chars = new char[0];
        this.length = 0;
    }

    public int find(char c) {
        for (int i = 0; i < length; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return new String(chars, 0, length);
    }

    public static void main(String[] args) {
        Task2 str1 = new Task2();
        System.out.println("Пустая строка: " + str1 + ", длина: " + str1.getLength());

        Task2 str2 = new Task2("Привет");
        System.out.println("Строка: " + str2 + ", длина: " + str2.getLength());
        System.out.println("Поиск 'и': " + str2.find('и'));

        Task2 str3 = new Task2('A');
        System.out.println("Символ: " + str3 + ", длина: " + str3.getLength());

        str2.clear();
        System.out.println("После очистки: " + str2 + ", длина: " + str2.getLength());
    }
}