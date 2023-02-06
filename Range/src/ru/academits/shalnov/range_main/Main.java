package ru.academits.shalnov.range_main;

import ru.academits.shalnov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало диапазона 1:");
        double from1 = scanner.nextDouble();

        System.out.println("Введите конец диапазона 1:");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);

        System.out.println("Диапазон 1:");
        System.out.println(range1);
        System.out.println("Длина диапазона = " + range1.getLength());

        System.out.println("Введите начало диапазона 2:");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец диапазона 2:");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);

        System.out.println("Диапазон 2:");
        System.out.println(range2);
        System.out.println("Длина диапазона = " + range2.getLength());

        System.out.println();

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("У заданных диапазонов нет пересечения");
        } else {
            System.out.println("Диапазон пересечения:");
            System.out.println(intersection);
        }

        System.out.println();

        Range[] arrayUnionRanges = range1.getUnion(range2);
        System.out.println("Количество диапазонов полученных в результате объединения = " + arrayUnionRanges.length);

        for (int i = 0; i < arrayUnionRanges.length; i++) {
            System.out.println("Диапазон " + (i + 1) + ":");
            System.out.println(arrayUnionRanges[i]);
        }

        System.out.println();
        Range[] arrayDifferenceRanges = range1.getDifference(range2);
        System.out.println("Количество диапазонов полученных в результате разности = " + arrayDifferenceRanges.length);

        for (int i = 0; i < arrayDifferenceRanges.length; i++) {
            System.out.println("Диапазон " + (i + 1) + ":");
            System.out.println(arrayDifferenceRanges[i]);
        }

        System.out.println("Введите точку для проверки:");
        double point1 = scanner.nextDouble();

        if (range1.isInside(point1)) {
            System.out.println("Точка принадлежит диапазону 1");
        } else {
            System.out.println("Точка не принадлежит диапазону 1");
        }

        System.out.println();

        System.out.println("Введите новое начало диапазона 1:");
        from1 = scanner.nextDouble();

        System.out.println("Введите новый конец диапазона 1:");
        to1 = scanner.nextDouble();

        range1.setFrom(from1);
        range1.setTo(to1);

        System.out.println();

        System.out.println("Новый диапазон:");
        System.out.println(range1);
    }
}