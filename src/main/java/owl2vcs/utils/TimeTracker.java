package owl2vcs.utils;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.google.common.base.Strings;

public class TimeTracker extends LinkedHashMap<String, Interval> {
    private static final long serialVersionUID = 4253748581102583813L;
    private final int nanosecondsPerMillisecond = 1000000;

    public void start(final String name) {
        put(name, new Interval(System.nanoTime() / nanosecondsPerMillisecond));
    }

    public void end(final String name) {
        get(name).setEnd(System.nanoTime() / nanosecondsPerMillisecond);
    }

    public void outputTable(int width, PrintStream out) {
        // Heading
        for (final Entry<String, Interval> e : entrySet())
            out.print(Strings.padStart(e.getKey(), width, ' '));
        out.println();
        // Values
        for (final Entry<String, Interval> e : entrySet())
            out.print(Strings.padStart(e.getValue().toString(), width, ' '));
        out.println();
    }
}
