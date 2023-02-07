package ru.academits.shalnov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public Vector(int n) {
        vector = new double[n];
    }

    public Vector(Vector vector) {
        this.vector = new double[vector.vector.length];

        System.arraycopy(vector.vector, 0, this.vector, 0, vector.vector.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        vector = new double[array.length];

        System.arraycopy(array, 0, this.vector, 0, array.length);
    }

    public Vector(int n, double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("Размерность должна быть больше 0");
        }

        vector = new double[n];

        System.arraycopy(array, 0, this.vector, 0, array.length);
    }

    public int getSize() {
        return vector.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector).replace('[', '{').replace(']', '}');
    }

    public void addition(Vector vector) {
        if (this.vector.length < vector.vector.length) {
            double[] tempVector = new double[vector.vector.length];
            System.arraycopy(this.vector, 0, tempVector,
                    0, this.vector.length);

            this.vector = tempVector;
        }

        for (int i = 0; i < vector.vector.length; i++) {
            this.vector[i] += vector.vector[i];
        }
    }

    public void subtraction(Vector vector) {
        if (this.vector.length < vector.vector.length) {
            double[] tempVector = new double[vector.vector.length];
            System.arraycopy(this.vector, 0, tempVector,
                    0, this.vector.length);

            this.vector = tempVector;
        }

        for (int i = 0; i < vector.vector.length; i++) {
            this.vector[i] -= vector.vector[i];
        }
    }

    public void multiplicationByScalar(double scalar) {
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] *= scalar;
        }
    }

    public void expand() {
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] *= -1;
        }
    }

    public double getLength() {
        double sum = 0;
        for (double v : vector) {
            sum += Math.pow(v, 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int componentIndex) {
        if (componentIndex >= vector.length || componentIndex < 0) {
            throw new IllegalArgumentException("Запрошенного индекса нет в векторе");
        }

        return vector[componentIndex];
    }

    public void setComponent(int componentIndex, double componentValue) {
        if (componentIndex >= vector.length || componentIndex < 0) {
            throw new IllegalArgumentException("Запрошенного индекса нет в векторе");
        }

        vector[componentIndex] = componentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector1 = (Vector) o;
        return Arrays.equals(vector, vector1.vector) && vector.length == vector1.vector.length;
    }

    @Override
    public int hashCode() {
        final int prime = 43;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(vector);
        return hash;
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        vector3.addition(vector2);
        return vector3;
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        vector3.subtraction(vector2);
        return vector3;
    }

    public static double scalarVectorsProduct(Vector vector1, Vector vector2) {
        Vector smallerVector = new Vector(vector1.vector.length > vector2.vector.length ? vector2 : vector1);

        double scalarVectorsProduct = 0;

        for (int i = 0; i < smallerVector.vector.length; i++) {
            scalarVectorsProduct += vector1.vector[i] * vector2.vector[i];
        }

        return scalarVectorsProduct;
    }
}