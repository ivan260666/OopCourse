package ru.academits.shalnov.shape;

public class Triangle implements Shape {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return (x1 < x2 ? Math.max(x2, x3) : Math.max(x1, x3)) - (x1 > x2 ? Math.min(x2, x3) : Math.min(x1, x3));
    }

    @Override
    public double getHeight() {
        return (y1 < y2 ? Math.max(y2, y3) : Math.max(y1, y3)) - (y1 > y2 ? Math.min(y2, y3) : Math.min(y1, y3));
    }

    @Override
    public double getArea() {
        return 0.5 * (getHeight() * getWidth());
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) +
                Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2)) +
                Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    }

    @Override
    public String toString() {
        return "Фигура - треугольник, ширина фигуры = " + getWidth() + ", длина фигуры = " + getHeight() +
                ", периметр фигуры = " + getPerimeter() + ", площадь фигуры = " + getArea();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 && Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 && Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 && Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 42;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}