package ru.academits.shalnov.range_main;

import ru.academits.shalnov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало диапазона:");
        double rangeStart = scanner.nextDouble();

        System.out.println("Введите конец диапазона:");
        double rangeEnd = scanner.nextDouble();

        Range range = new Range(rangeStart, rangeEnd);

        System.out.println("Начало диапазона = " + range.getFrom());
        System.out.println("Конец диапазона = " + range.getTo());
        System.out.println("Длина диапазона = " + range.getLength());

        System.out.println("Введите точку для проверки:");
        double point1 = scanner.nextDouble();
        System.out.println();

        if (range.isInside(point1)) {
            System.out.println("Точка принадлежит диапазону");
        } else {
            System.out.println("Точка не принадлежит диапазону");
        }

        System.out.println();

        System.out.println("Введите новое начало диапазона:");
        rangeStart = scanner.nextDouble();

        System.out.println("Введите новый конец диапазона:");
        rangeEnd = scanner.nextDouble();

        range.setFrom(rangeStart);
        range.setTo(rangeEnd);

        System.out.println();
        System.out.println("Новое начало диапазона = " + range.getFrom());
        System.out.println("Новый конец диапазона = " + range.getTo());
        System.out.println("Новая длина диапазона = " + range.getLength());
    }
}