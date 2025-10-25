package Task3;/*
* Разработать программу для представления комплексных чисел с возможностью задания вещественной и
* мнимой частей, как числами типов double, так и целыми числами. Обеспечить выполнение операций:
- сравнения чисел;
- сложения;
- вычитания;
- умножения.
 

*
* */

public class Task3 implements OperableComplex {
    private double real;
    private double imag;

    public Task3(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Task3(int real, int imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    @Override
    public Task3 add(Task3 other) {
        return new Task3(this.real + other.real, this.imag + other.imag);
    }

    @Override
    public Task3 subtract(Task3 other) {
        return new Task3(this.real - other.real, this.imag - other.imag);
    }

    @Override
    public Task3 multiply(Task3 other) {
        double newReal = this.real * other.real - this.imag * other.imag;
        double newImag = this.real * other.imag + this.imag * other.real;
        return new Task3(newReal, newImag);
    }

    public boolean equals(Task3 other) {
        return this.real == other.real && this.imag == other.imag;
    }

    @Override
    public String toString() {
        return real + " + " + imag + "i";
    }

    public static void main(String[] args) {
        Task3 c1 = new Task3(2.0, 3.0);
        Task3 c2 = new Task3(1, 4);  // int конструктор

        System.out.println("Комплекс1: " + c1);
        System.out.println("Комплекс2: " + c2);
        System.out.println("Сложение: " + c1.add(c2));
        System.out.println("Вычитание: " + c1.subtract(c2));
        System.out.println("Умножение: " + c1.multiply(c2));
        System.out.println("Равны: " + c1.equals(c2));
    }
}