package ru.academits.shalnov.shape;

public class Rectangle implements Shape {
    private final double widthLength;
    private final double heightLength;

    public Rectangle(double widthLength, double heightLength) {
        this.widthLength = widthLength;
        this.heightLength = heightLength;
    }

    @Override
    public double getWidth() {
        return widthLength;
    }

    @Override
    public double getHeight() {
        return heightLength;
    }

    @Override
    public double getArea() {
        return heightLength * widthLength;
    }

    @Override
    public double getPerimeter() {
        return (heightLength * 2) + (widthLength * 2);
    }

    @Override
    public String toString() {
        return "Фигура - прямоугольник, ширина фигуры = " + getWidth() + ", длина фигуры = " + getHeight() +
                ", периметр фигуры = " + getPerimeter() + ", площадь фигуры = " + getArea();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.widthLength, widthLength) == 0 &&
                Double.compare(rectangle.heightLength, heightLength) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(widthLength);
        hash = prime * hash + Double.hashCode(heightLength);
        return hash;
    }
}