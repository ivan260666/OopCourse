package ru.academits.shalnov.shape;

public class Square implements Shape {
    double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Фигура - квадрат, ширина фигуры = " + getWidth() + ", длина фигуры = " + getHeight() +
                ", периметр фигуры = " + getPerimeter() + ", площадь фигуры = " + getArea();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.sideLength, sideLength) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 42;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
}