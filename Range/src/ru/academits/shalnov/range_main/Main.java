package ru.academits.shalnov.range_main;

import ru.academits.shalnov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало диапазона 1:");
        double rangeStart1 = scanner.nextDouble();

        System.out.println("Введите конец диапазона 1:");
        double rangeEnd1 = scanner.nextDouble();

        Range range1 = new Range(rangeStart1, rangeEnd1);

        System.out.println("Начало диапазона 1 = " + range1.getFrom());
        System.out.println("Конец диапазона 1 = " + range1.getTo());
        System.out.println("Длина диапазона 1 = " + range1.getLength());

        System.out.println("Введите начало диапазона 2:");
        double rangeStart2 = scanner.nextDouble();

        System.out.println("Введите конец диапазона 2:");
        double rangeEnd2 = scanner.nextDouble();

        Range range2 = new Range(rangeStart2, rangeEnd2);

        System.out.println("Начало диапазона 2 = " + range2.getFrom());
        System.out.println("Конец диапазона 2 = " + range2.getTo());
        System.out.println("Длина диапазона 2 = " + range2.getLength());

        System.out.println();

        Range crossingRange = range1.getCrossingRange(range2);

        if (crossingRange == null) {
            System.out.println("У заданных диапазонов нет пересечения");
        } else {
            System.out.println("Начало диапазона пересечения = " + crossingRange.getFrom());

            System.out.println("Конец диапазона пересечения = " + crossingRange.getTo());
        }

        System.out.println();

        Range[] arrayUnionRanges = range1.getRangeIntervalsUnion(range2);
        System.out.println("Количество диапазонов полученых в результате объединения = " + arrayUnionRanges.length);

        for (int i = 0; i < arrayUnionRanges.length; i++) {
            System.out.println("Начало диапазона " + (i + 1) + " = " + arrayUnionRanges[i].getFrom());
            System.out.println("Конец диапазона " + (i + 1) + " = " + arrayUnionRanges[i].getTo());
        }

        System.out.println();

        if (range1.getDifferenceBetweenRanges(range2) == null) {
            System.out.println("Количество диапазонов полученых в результате разности = 0");
        } else {
            Range[] arrayDifferenceRanges = range1.getDifferenceBetweenRanges(range2);
            System.out.println("Количество диапазонов полученых в результате разности = " + arrayDifferenceRanges.length);

            for (int i = 0; i < arrayDifferenceRanges.length; i++) {
                System.out.println("Начало диапазона " + (i + 1) + " = " + arrayDifferenceRanges[i].getFrom());
                System.out.println("Конец диапазона " + (i + 1) + " = " + arrayDifferenceRanges[i].getTo());
            }
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
        rangeStart1 = scanner.nextDouble();

        System.out.println("Введите новый конец диапазона 1:");
        rangeEnd1 = scanner.nextDouble();

        range1.setFrom(rangeStart1);
        range1.setTo(rangeEnd1);

        System.out.println();

        System.out.println("Новое начало диапазона 1 = " + range1.getFrom());
        System.out.println("Новый конец диапазона 1 = " + range1.getTo());
        System.out.println("Новая длина диапазона 1 = " + range1.getLength());
    }
}