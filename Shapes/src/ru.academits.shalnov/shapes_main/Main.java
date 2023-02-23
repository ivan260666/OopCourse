package ru.academits.shalnov.shapes_main;

import ru.academits.shalnov.shapes.*;

import java.util.Arrays;

public class Main {

    public static Shape getShapeByAreaOrder(Shape[] shapes, int numberFromEnd) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        ShapesAreaComparator comparator = new ShapesAreaComparator();
        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - numberFromEnd];
    }

    public static Shape getShapeByPerimeterOrder(Shape[] shapes, int numberFromEnd) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        ShapesPerimeterComparator comparator = new ShapesPerimeterComparator();
        Arrays.sort(shapes, comparator);
        return shapes[shapes.length - numberFromEnd];
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(500),
                new Triangle(50, 50, 100, 100, 100, 0),
                new Square(10),
                new Rectangle(5, 10),
                new Square(13),
                new Circle(8)};

        System.out.println("Фигура с самой большой площадью:");
        System.out.println(getShapeByAreaOrder(shapes, 1));

        System.out.println("Фигура на втором месте по периметру:");
        System.out.println(getShapeByPerimeterOrder(shapes, 2));
    }
}