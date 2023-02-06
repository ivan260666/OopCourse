package ru.academits.shalnov.shape_main;

import ru.academits.shalnov.shape.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static Shape getFigureByAreaOrder(Shape[] shapes, int orderPlace) {
        Comparator<Shape> comparatorByArea = Comparator.comparing(Shape::getArea);
        Arrays.sort(shapes, comparatorByArea);
        return shapes[shapes.length - orderPlace];
    }

    public static Shape getFigureByPerimeterOrder(Shape[] shapes, int orderPlace) {
        Comparator<Shape> comparatorByPerimeter = Comparator.comparing(Shape::getPerimeter);
        Arrays.sort(shapes, comparatorByPerimeter);
        return shapes[shapes.length - orderPlace];
    }

    public static void main(String[] args) {
        Shape shape1 = new Circle(5);
        Shape shape2 = new Triangle(5, 5, 10, 10, 10, 0);
        Shape shape3 = new Square(10);
        Shape shape4 = new Rectangle(5, 10);
        Shape shape5 = new Square(13);
        Shape shape6 = new Circle(8);
        Shape[] shapes = {shape1, shape2, shape3, shape4, shape5, shape6};

        System.out.println("Фигура с самой большой площадью:");
        System.out.println(getFigureByAreaOrder(shapes, 1));

        System.out.println("Фигура на втором месте по периметру:");
        System.out.println(getFigureByPerimeterOrder(shapes, 2));
    }
}