package ru.academits.shalnov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Переданная размерность = " + size + ". Размерность должна быть больше 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Вектор отсутствует");
        }

        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив отсутствует");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("длина переданного массива = 0. Размерность вектора должна быть больше 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив отсутствует");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Переданная размерность = " + size + ". Размерность должна быть больше 0");
        }

        components = Arrays.copyOf(array, size);
    }

    public double[] getComponents() {
        return components;
    }

    public void setComponents(double[] components) {
        this.components = components;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(Arrays.toString(components));
        result.replace(0, 1, "{").replace(result.length() - 1, result.length(), "}");

        return result.toString();
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void deploy() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за допустимые границы. " +
                    "Переданный индекс = " + index + ", границы - (" + 0 + ", " + (components.length - 1) + ")");
        }

        return components[index];
    }

    public void setComponent(int index, double componentValue) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за допустимые границы. " +
                    "Переданный индекс = " + index + ", границы - (" + 0 + ", " + (components.length - 1) + ")");
        }

        components[index] = componentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 43;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public static Vector getAmount(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);
        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);
        return result;
    }

    public static double getInnerProduct(Vector vector1, Vector vector2) {
        double innerProduct = 0;

        for (int i = 0; i < vector1.components.length && i < vector2.components.length; i++) {
            innerProduct += vector1.components[i] * vector2.components[i];
        }

        return innerProduct;
    }
}