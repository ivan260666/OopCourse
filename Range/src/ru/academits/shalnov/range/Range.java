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

    public Range getCrossingRange(Range range2) {
        double rangeStart1 = this.getFrom();
        double rangeEnd1 = this.getTo();

        double rangeStart2 = range2.getFrom();
        double rangeEnd2 = range2.getTo();

        double crossingRangeStart = Math.max(rangeStart1, rangeStart2);
        double crossingRangeEnd = Math.min(rangeEnd1, rangeEnd2);

        if ((this.isInside(crossingRangeStart) && this.isInside(crossingRangeStart)) &&
                (range2.isInside(crossingRangeStart) && range2.isInside(crossingRangeEnd)) &&
                (crossingRangeStart != crossingRangeEnd)) {
            return new Range(crossingRangeStart, crossingRangeEnd);
        }

        return null;
    }

    public Range[] getRangeIntervalsUnion(Range range2) {
        double rangeStart1 = this.getFrom();
        double rangeEnd1 = this.getTo();

        double rangeStart2 = range2.getFrom();
        double rangeEnd2 = range2.getTo();

        double crossingRangeStart = Math.max(rangeStart1, rangeStart2);
        double crossingRangeEnd = Math.min(rangeEnd1, rangeEnd2);

        if ((this.isInside(crossingRangeStart) && this.isInside(crossingRangeStart)) &&
                (range2.isInside(crossingRangeStart) && range2.isInside(crossingRangeEnd))) {
            return new Range[]{new Range(Math.min(rangeStart1, rangeStart2), Math.max(rangeEnd1, rangeEnd2))};
        }

        return new Range[]{new Range(rangeStart1, rangeEnd1), new Range(rangeStart2, rangeEnd2)};
    }

    public Range[] getDifferenceBetweenRanges(Range subtractedRange) {
        double rangeStart = this.getFrom();
        double rangeEnd = this.getTo();

        double subtrahendRangeStart = subtractedRange.getFrom();
        double subtrahendRangeEnd = subtractedRange.getTo();

        if (subtractedRange.isInside(rangeStart) && subtractedRange.isInside(rangeEnd)) {
            return null;
        }

        if (subtractedRange.isInside(rangeStart)) {
            return new Range[]{new Range(subtrahendRangeEnd, rangeEnd)};
        }

        if (subtractedRange.isInside(rangeEnd)) {
            return new Range[]{new Range(rangeStart, subtrahendRangeStart)};
        }

        if (this.getCrossingRange(subtractedRange) != null) {
            return new Range[]{new Range(rangeStart, subtrahendRangeStart), new Range(subtrahendRangeEnd, rangeEnd)};
        }

        return new Range[]{new Range(rangeStart, rangeEnd)};
    }
}