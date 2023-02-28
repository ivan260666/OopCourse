package ru.academits.shalnov.matrix_main;

import ru.academits.shalnov.matrix.Matrix;
import ru.academits.shalnov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] vectorsArray1 = {{1, 2}, {3, 4}};
        Vector[] vectorsArray2 = new Vector[2];

        for (int i = 0; i < vectorsArray2.length; i++) {
            vectorsArray2[i] = new Vector(2);
        }


        Matrix matrix1 = new Matrix(1, 2);
        System.out.println("Матрица 1 - " + matrix1);

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("Матрица 2 - " + matrix2);

        Matrix matrix3 = new Matrix(vectorsArray1);
        System.out.println("Матрица 3 - " + matrix3);

        Matrix matrix4 = new Matrix(vectorsArray2);
        System.out.println("Матрица 4 - " + matrix4);

        System.out.println("Первый вектор третьей матрицы - " + matrix3.getVectors()[0]);

        matrix2.setVectors(vectorsArray2);

        System.out.println("Размер строки третьей матрицы = " + matrix3.getRowSize());
        System.out.println("Размер столбца третьей матрицы = " + matrix3.getColumnSize());

        System.out.println("1 строка третьей матрицы = " + matrix3.getRowVector(0));
        matrix2.setRowVector(0, matrix3.getColumnVector(0));

        matrix3.transpose();

        matrix1.multiplyByScalar(5);

        System.out.println("Определитель второй матрицы = " + matrix2.getDeterminant());

        System.out.println("Вектор полученный в результате умножения матрицы на вектор-столбец = " +
                matrix3.multiplyByVector_Column(vectorsArray2[0]));

        System.out.println("Матрица полученная в результате умножения матрицы на вектор-строку = " +
                matrix1.multiplyByVector_Row(vectorsArray2[0]));

        matrix3.subtract(matrix4);
        matrix3.add(matrix3);

        System.out.println("Матрица полученная в результате сложения матриц 3 и 4 = " +
                Matrix.getAmount(matrix3, matrix4));

        System.out.println("Матрица полученная в результате вычитания матрицы 3 из матрицы 4 = "
                + Matrix.getDifference(matrix3, matrix4));

        System.out.println("Матрица полученная в результате умножения матрицы 3 на матрицу 4 = "
                + Matrix.getProduct(matrix3, matrix4));

        System.out.println("Матрица 1 после отработанной программы - " + matrix1);
        System.out.println("Матрица 2 после отработанной программы - " + matrix2);
        System.out.println("Матрица 3 после отработанной программы - " + matrix3);
        System.out.println("Матрица 4 после отработанной программы - " + matrix4);
    }
}