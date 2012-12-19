package owl2vcs.utils;

public class Interval {
    private Long start;

    public Long getStart() {
        return start;
    }

    public void setStart(final Long start) {
        this.start = start;
    }

    private Long end;

    public Long getEnd() {
        return end;
    }

    public void setEnd(final Long end) {
        this.end = end;
    }

    public Interval(final Long start) {
        this.start = start;
    }

    public Interval(final Long start, final Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return (end - start) + "";
    }
}
