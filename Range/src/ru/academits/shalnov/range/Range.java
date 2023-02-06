package ru.academits.shalnov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        double epsilon = 1e-10;

        return number - from >= -epsilon && number - to <= epsilon;
    }

    public Range getIntersection(Range range) {
        double from1 = from;
        double to1 = to;

        double from2 = range.from;
        double to2 = range.to;

        double intersectionFrom = Math.max(from1, from2);
        double intersectionTo = Math.min(to1, to2);

        if ((intersectionFrom >= Math.min(from1, from2) && intersectionFrom <= Math.max(to1, to2)) &&
                (intersectionTo >= Math.min(from1, from2) && intersectionTo <= Math.max(to1, to2)) &&
                (intersectionFrom < intersectionTo)) {
            return new Range(intersectionFrom, intersectionTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        double from1 = from;
        double to1 = to;

        double from2 = range.from;
        double to2 = range.to;

        double intersectionFrom = Math.max(from1, from2);
        double intersectionTo = Math.min(to1, to2);

        if ((intersectionFrom >= Math.min(from1, from2) && intersectionFrom <= Math.max(to1, to2)) &&
                (intersectionTo >= Math.min(from1, from2) && intersectionTo <= Math.max(to1, to2)) &&
                (intersectionFrom <= intersectionTo)) {
            return new Range[]{new Range(Math.min(from1, from2), Math.max(to1, to2))};
        }

        return new Range[]{new Range(from1, to1), new Range(from2, to2)};
    }

    public Range[] getDifference(Range subtractedRange) {
        double from = this.from;
        double to = this.to;

        double subtrahendRangeFrom = subtractedRange.from;
        double subtrahendRangeTo = subtractedRange.to;

        if ((from >= subtrahendRangeFrom && from <= subtrahendRangeTo) &&
                (to >= subtrahendRangeFrom && to <= subtrahendRangeTo)) {
            return new Range[]{};
        }

        if (from >= subtrahendRangeFrom && from <= subtrahendRangeTo) {
            return new Range[]{new Range(subtrahendRangeTo, to)};
        }

        if (to >= subtrahendRangeFrom && to <= subtrahendRangeTo) {
            return new Range[]{new Range(from, subtrahendRangeFrom)};
        }

        if (this.getIntersection(subtractedRange) != null) {
            return new Range[]{new Range(from, subtrahendRangeFrom), new Range(subtrahendRangeTo, to)};
        }

        return new Range[]{new Range(from, to)};
    }

    @Override
    public String toString() {
        return "Начало диапазона = " + from + ", конец диапазона = " + to;
    }
}