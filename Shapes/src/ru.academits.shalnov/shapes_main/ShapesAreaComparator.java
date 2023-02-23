package ru.academits.shalnov.shapes_main;

import ru.academits.shalnov.shapes.Shape;

import java.util.Comparator;

public class ShapesAreaComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}