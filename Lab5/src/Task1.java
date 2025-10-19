import java.util.Scanner;

public class Task1 {
    /*
    * Написать и протестировать методы работы с квадратными матрицами (матрицы представить в виде двухмерных массивов).
    * Должны присутствовать методы:
•	создания единичной (диагональной) матрицы;
•	создания нулевой матрицы;
•	сложение матриц;
•	умножения матриц;
•	умножение матрицы на скаляр;
•	определение детерминанта матрицы;
•	вывод матрицы на консоль.

    * */
    public static int[][] createIdentityMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }
        return matrix;
    }

    public static int[][] createZeroMatrix(int size) {
        return new int[size][size];
    }

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int size = a.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int size = a.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] multiplyMatrixByScalar(int[][] matrix, int scalar) {
        int size = matrix.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    public static int determinant(int[][] matrix) {
        int size = matrix.length;

        if (size == 1) return matrix[0][0];
        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int det = 0;
        for (int col = 0; col < size; col++) {
            int[][] minor = getMinor(matrix, 0, col);
            int sign = (col % 2 == 0) ? 1 : -1;
            det += sign * matrix[0][col] * determinant(minor);
        }
        return det;
    }

    private static int[][] getMinor(int[][] matrix, int row, int col) {
        int size = matrix.length;
        int[][] minor = new int[size - 1][size - 1];

        int minorRow = 0;
        for (int i = 0; i < size; i++) {
            if (i == row) continue;
            int minorCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == col) continue;
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер матрицы: ");
        int size = scanner.nextInt();

        // Создание и вывод единичной матрицы
        System.out.println("Единичная матрица:");
        int[][] identity = createIdentityMatrix(size);
        printMatrix(identity);

        // Создание и вывод нулевой матрицы
        System.out.println("\nНулевая матрица:");
        int[][] zero = createZeroMatrix(size);
        printMatrix(zero);

        // Создание тестовых матриц
        int[][] matrixA = createTestMatrix(size);
        int[][] matrixB = createTestMatrix(size);

        System.out.println("\nМатрица A:");
        printMatrix(matrixA);

        System.out.println("\nМатрица B:");
        printMatrix(matrixB);

        // Сложение матриц
        System.out.println("\nСумма матриц A + B:");
        int[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        // Умножение матриц
        System.out.println("\nПроизведение матриц A * B:");
        int[][] product = multiplyMatrices(matrixA, matrixB);
        printMatrix(product);

        // Умножение на скаляр
        System.out.println("\nМатрица A * 3:");
        int[][] scaled = multiplyMatrixByScalar(matrixA, 3);
        printMatrix(scaled);

        // Детерминант
        if (size <= 3) {
            System.out.println("\nДетерминант матрицы A: " + determinant(matrixA));
        }

        scanner.close();
    }

    private static int[][] createTestMatrix(int size) {
        int[][] matrix = new int[size][size];
        int counter = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = counter++;
            }
        }
        return matrix;
    }
}