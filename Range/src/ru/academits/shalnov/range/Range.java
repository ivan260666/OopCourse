package ru.academits.shalnov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
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
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if ((intersectionFrom <= Math.max(to, range.to)) &&
                (intersectionTo >= Math.min(from, range.from)) &&
                (intersectionFrom < intersectionTo)) {
            return new Range(intersectionFrom, intersectionTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if ((intersectionFrom <= Math.max(to, range.to)) &&
                (intersectionTo >= Math.min(from, range.from)) &&
                (intersectionFrom <= intersectionTo)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range subtractedRange) {
        if (!((Math.max(from, subtractedRange.from) <= Math.max(to, subtractedRange.to)) &&
                (Math.min(to, subtractedRange.to) >= Math.min(from, subtractedRange.from)) &&
                (Math.max(from, subtractedRange.from) < Math.min(to, subtractedRange.to)))) {
            return new Range[]{new Range(from, to)};
        }

        if ((from >= subtractedRange.from) &&
                (to <= subtractedRange.to)) {
            return new Range[]{};
        }

        if (from >= subtractedRange.from) {
            return new Range[]{new Range(subtractedRange.to, to)};
        }

        if (to <= subtractedRange.to) {
            return new Range[]{new Range(from, subtractedRange.from)};
        }

        return new Range[]{new Range(from, subtractedRange.from), new Range(subtractedRange.to, to)};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}