package ru.academits.shalnov.matrix;

import ru.academits.shalnov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;
    private double determinant;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Переданная размерность = " + n + " x " + m +
                    ". Размерность должна быть больше 0");
        }

        vectors = new Vector[m];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Матрица отсутствует");
        }

        vectors = Arrays.copyOf(matrix.vectors, matrix.vectors.length);
    }

    public Matrix(double[][] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Массив векторов отсутствует");
        }

        if (vectors.length == 0 || vectors[0].length == 0) {
            throw new IllegalArgumentException("Переданная размерность = " + vectors.length + " x "
                    + vectors[0].length + ". Размерность должна быть больше 0");
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(vectors[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Массив векторов отсутствует");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Переданная размерность = " + vectors.length +
                    ". Размерность должна быть больше 0");
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(vectors[i]);
        }
    }

    public Vector[] getVectors() {
        return vectors;
    }

    public void setVectors(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Массив векторов отсутствует");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Переданная размерность = " + vectors.length +
                    ". Размерность должна быть больше 0");
        }

        this.vectors = Arrays.copyOf(vectors, vectors.length);
    }

    public int getRowSize() {
        return vectors[0].getSize();
    }

    public int getColumnSize() {
        return vectors.length;
    }

    public Vector getRowVector(int rowVectorIndex) {
        return vectors[rowVectorIndex];
    }

    public void setRowVector(int rowVectorIndex, Vector vector) {
        if (rowVectorIndex < 0 || rowVectorIndex >= vectors.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за допустимые границы. " +
                    "Переданный индекс = " + rowVectorIndex + ", границы - (" + 0 + ", " + (vectors.length - 1) + ")");
        }

        if (vector == null) {
            throw new IllegalArgumentException("Вектор отсутствует");
        }

        if (vector.getSize() != getRowSize()) {
            throw new IllegalArgumentException("Переданная размерность вектора = " + vector.getSize() +
                    ". Размерность должна быть равна " + getRowSize());
        }

        vectors[rowVectorIndex] = new Vector(vector);
    }

    public Vector getColumnVector(int columnVectorIndex) {
        if (columnVectorIndex < 0 || columnVectorIndex >= getRowSize()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за допустимые границы. " +
                    "Переданный индекс = " + columnVectorIndex + ", границы - (" + 0 + ", " + (getRowSize() - 1) + ")");
        }

        Vector vector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            vector.setComponent(i, vectors[i].getComponent(columnVectorIndex));
        }

        return vector;
    }

    public void transpose() {
        Matrix temp = new Matrix(vectors.length, vectors[0].getSize());

        for (int i = 0; i < vectors.length; i++) {
            temp.vectors[i] = new Vector(getColumnVector(i));
        }

        vectors = temp.vectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : vectors) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (getRowSize() != getColumnSize()) {
            throw new IllegalArgumentException("Определитель можно найти только у квадратной матрици," +
                    " размерность переданной матрицы = " + getRowSize() + "x" + getColumnSize());
        }

        getDeterminant(this, 1);

        return determinant;
    }

    private void getDeterminant(Matrix subMinor, double parentMinor) {
        if (subMinor.getColumnSize() > 1) {
            Matrix tempMinor = new Matrix(subMinor.getColumnSize() - 1, subMinor.getRowSize() - 1);

            for (int i = 0; i < subMinor.getRowSize(); i++) {
                for (int j = 1; j < subMinor.getColumnSize(); j++) {
                    for (int v = 0; v < subMinor.getRowSize(); v++) {
                        if (v < i)
                            tempMinor.getRowVector(j - 1).
                                    setComponent(v, subMinor.getRowVector(j).getComponent(v));
                        else if (v > i)
                            tempMinor.getRowVector(j - 1).
                                    setComponent(v - 1, subMinor.getRowVector(j).getComponent(v));
                    }
                }

                double parameterForSubMinor = Math.pow(-1, i + 2) * subMinor.getRowVector(0).
                        getComponent(i) * parentMinor;
                getDeterminant(tempMinor, parameterForSubMinor);
            }
        } else {
            determinant += parentMinor * subMinor.getRowVector(0).getComponent(0);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < vectors.length - 1; i++) {
            stringBuilder.append(vectors[i].toString()).append(",");
        }

        stringBuilder.append(vectors[vectors.length - 1].toString()).append("}");
        return stringBuilder.toString();
    }

    public Vector multiplyByVector_Column(Vector vector) {
        if (vector.getSize() != getColumnSize()) {
            throw new IllegalArgumentException("Число столбцов в матрице и число строк в векторе-строке должно совпадать." +
                    System.lineSeparator() + "Число столбцов =" + getColumnSize() +
                    ", число строк в векторе = " + vector.getSize());
        }

        Vector result = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            result.setComponent(i, Vector.getInnerProduct(vector, vectors[i]));
        }

        return result;
    }

    public Matrix multiplyByVector_Row(Vector vector) {
        if (getRowSize() != 1) {
            throw new IllegalArgumentException("Матрица должна быть вектором столбцом. Длина строки переданной матрицы = " +
                    getRowSize());
        }

        if (getColumnSize() != vector.getSize()) {
            throw new IllegalArgumentException("Число строк матрицы должно быть равно числу столбцов вектора строки." +
                    System.lineSeparator() + "Число строк матрицы = " + getColumnSize() +
                    ", число столбцов вектора = " + vector.getSize());

        }

        Vector[] result = new Vector[getColumnSize()];

        for (int i = 0; i < getColumnSize(); i++) {
            result[i] = new Vector(getColumnSize());

            for (int j = 0; j < getColumnSize(); j++) {
                result[i].setComponent(j, vectors[i].getComponent(0) * vector.getComponent(j));
            }
        }

        return new Matrix(result);
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Матрица отсутствует");
        }

        if (getColumnSize() != matrix.getColumnSize() || getRowSize() != matrix.getRowSize()) {
            throw new IllegalArgumentException("Разменость матриц должна быть одинаковой." + System.lineSeparator() +
                    "Размерность первой матрицы - " + getRowSize() + "x" + getColumnSize() + System.lineSeparator() +
                    "Размерность второй матрицы - " + matrix.getRowSize() + "x" + matrix.getColumnSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Матрица отсутствует");
        }

        if (getColumnSize() != matrix.getColumnSize() || getRowSize() != matrix.getRowSize()) {
            throw new IllegalArgumentException("Разменость матриц должна быть одинаковой." + System.lineSeparator() +
                    "Размерность первой матрицы - " + getRowSize() + "x" + getColumnSize() + System.lineSeparator() +
                    "Размерность второй матрицы - " + matrix.getRowSize() + "x" + matrix.getColumnSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            vectors[i].add(matrix.vectors[i]);
        }
    }

    public static Matrix getAmount(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new IllegalArgumentException("Матрица отсутствует");
        }

        if (matrix1.getColumnSize() != matrix2.getColumnSize() || matrix1.getRowSize() != matrix2.getRowSize()) {
            throw new IllegalArgumentException("Разменость матриц должна быть одинаковой." + System.lineSeparator() +
                    "Размерность первой матрицы - " + matrix1.getRowSize() + "x" + matrix1.getColumnSize() +
                    System.lineSeparator() + "Размерность второй матрицы - " + matrix2.getRowSize() + "x" +
                    matrix2.getColumnSize());
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new IllegalArgumentException("Матрица отсутствует");
        }

        if (matrix1.getColumnSize() != matrix2.getColumnSize() || matrix1.getRowSize() != matrix2.getRowSize()) {
            throw new IllegalArgumentException("Разменость матриц должна быть одинаковой." + System.lineSeparator() +
                    "Размерность первой матрицы - " + matrix1.getRowSize() + "x" + matrix1.getColumnSize() +
                    System.lineSeparator() + "Размерность второй матрицы - " + matrix2.getRowSize() + "x" +
                    matrix2.getColumnSize());
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new IllegalArgumentException("Матрица отсутствует");
        }

        if (matrix1.getColumnSize() != matrix2.getRowSize()) {
            throw new IllegalArgumentException("Количество строк первой матрицы должно быть равно" +
                    " количеству столбцов второй матрицы" + System.lineSeparator() +
                    "Размерность первой матрицы - " + matrix1.getRowSize() + "x" + matrix1.getColumnSize() +
                    System.lineSeparator() + "Размерность второй матрицы - " + matrix2.getRowSize() + "x" +
                    matrix2.getColumnSize());
        }

        Matrix result = new Matrix(matrix1.getColumnSize(), matrix2.getRowSize());

        for (int i = 0; i < result.getColumnSize(); i++) {
            for (int j = 0; j < result.getRowSize(); j++) {
                result.vectors[i].setComponent(j, Vector.
                        getInnerProduct(matrix1.getRowVector(i), matrix2.getColumnVector(j)));
            }
        }

        return result;
    }
}