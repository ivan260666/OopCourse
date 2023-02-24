package ru.academits.shalnov.vector_main;

import ru.academits.shalnov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = new double[5];

        for (int i = 0; i < array1.length; i++) {
            array1[i] = i;
        }

        Vector vector1 = new Vector(3, array1);
        System.out.println("Вектор 1 - " + vector1);

        Vector vector2 = new Vector(vector1);
        System.out.println("Вектор 2 - " + vector2);

        Vector vector3 = new Vector(10, array1);
        System.out.println("Вектор 3 - " + vector3);

        vector2.add(vector3);
        System.out.println("Вектор полученный в результате сложения вектора 2 и вектора 3 - " + vector2);

        vector2.subtract(vector3);
        System.out.println("Вектор полученный в результате вычитания вектора 3 из вектора 2 - " + vector2);

        vector2.multiplyByScalar(10);
        System.out.println("Вектор полученный в результате умножения вектора 2 на скаляр 10 - " + vector2);

        vector2.deploy();
        System.out.println("Вектор полученный в результате разворота вектора 2 - " + vector2);
        vector2.deploy();

        System.out.println("Длина вектора 2 = " + vector2.getLength());

        System.out.println("Размерность вектора 2 = " + vector2.getSize());

        System.out.println("Компонента вектора 2 под индексом 0 = " + vector2.getComponent(0));
        vector2.setComponent(0, 5);
        System.out.println("Новая компонента вектора 2 под индексом 0 = " + vector2.getComponent(0));

        Vector vector4 = Vector.getAmount(vector2, vector1);
        System.out.println("Вектор полученный в результате сложение вектора два с вектором 1 - " + vector4);

        Vector vector5 = Vector.getDifference(vector2, vector1);
        System.out.println("Вектор полученный в результате вычитания 1 вектора из 2 вектора - " + vector5);

        System.out.println("Скалярное произведение вектора 4 и вектора 1 = " + Vector.getInnerProduct(vector4, vector1));

        Vector vector6 = new Vector(10);
        System.out.println("Вектор 6 - " + vector6);
    }
}