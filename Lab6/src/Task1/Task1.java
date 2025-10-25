package Task1;

import java.util.Scanner;

/*
* Создать класс Money (Деньги) для работы с денежными суммами. Число должно быть представлено двумя полями:
- типа long – для рублей;
- типа byte – для копеек.
Реализовать вывод значения на экран, при этом дробная часть должна быть отделена от целой части запятой.
*  Реализовать сложение, вычитание, деление сумм, деление суммы на дробное число, умножение на дробное число и операции сравнения

*
* */


public class Task1 implements ComparableMoney {
    private long rubles;
    private byte kopecks;

    public Task1(long rubles, byte kopecks) {
        this.rubles = rubles + (kopecks / 100);
        this.kopecks = (byte) (kopecks % 100);
        if (this.kopecks < 0) {
            this.rubles -= 1;
            this.kopecks += 100;
        }
    }

    public long getRubles() {
        return rubles;
    }

    public byte getKopecks() {
        return kopecks;
    }

    public Task1 add(Task1 other) {
        long newRubles = this.rubles + other.rubles;
        byte newKopecks = (byte) (this.kopecks + other.kopecks);
        return new Task1(newRubles, newKopecks);
    }

    public Task1 subtract(Task1 other) {
        long newRubles = this.rubles - other.rubles;
        byte newKopecks = (byte) (this.kopecks - other.kopecks);
        return new Task1(newRubles, newKopecks);
    }

    public double divideByMoney(Task1 other) {
        double thisTotal = this.rubles + this.kopecks / 100.0;
        double otherTotal = other.rubles + other.kopecks / 100.0;
        return thisTotal / otherTotal;
    }

    public Task1 divideByDouble(double divisor) {
        double total = (this.rubles + this.kopecks / 100.0) / divisor;
        long newRubles = (long) total;
        byte newKopecks = (byte) ((total - newRubles) * 100);
        return new Task1(newRubles, newKopecks);
    }

    public Task1 multiplyByDouble(double multiplier) {
        double total = (this.rubles + this.kopecks / 100.0) * multiplier;
        long newRubles = (long) total;
        byte newKopecks = (byte) ((total - newRubles) * 100);
        return new Task1(newRubles, newKopecks);
    }

    @Override
    public int compareTo(Task1 other) {
        if (this.rubles != other.rubles) {
            return Long.compare(this.rubles, other.rubles);
        }
        return Byte.compare(this.kopecks, other.kopecks);
    }

    public boolean equals(Task1 other) {
        return this.compareTo(other) == 0;
    }

    @Override
    public String toString() {
        return rubles + "," + (kopecks < 10 ? "0" + kopecks : kopecks);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите рубли и копейки для первой суммы:");
        Task1 money1 = new Task1(scanner.nextLong(), scanner.nextByte());
        System.out.println("Введите рубли и копейки для второй суммы:");
        Task1 money2 = new Task1(scanner.nextLong(), scanner.nextByte());

        System.out.println("Сумма1: " + money1);
        System.out.println("Сумма2: " + money2);
        System.out.println("Сложение: " + money1.add(money2));
        System.out.println("Вычитание: " + money1.subtract(money2));
        System.out.println("Деление сумм: " + money1.divideByMoney(money2));
        System.out.println("Деление на 2.5: " + money1.divideByDouble(2.5));
        System.out.println("Умножение на 1.5: " + money1.multiplyByDouble(1.5));
        System.out.println("Сравнение: " + money1.compareTo(money2));
        System.out.println("Равны: " + money1.equals(money2));
    }
}