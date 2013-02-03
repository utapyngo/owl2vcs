package owl2vcs.utils;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.google.common.base.Strings;

public class TimeTracker extends LinkedHashMap<String, Interval> {
    private static final long serialVersionUID = 4253748581102583813L;
    private final int nanosecondsPerMillisecond = 1000000;
    private ThreadMXBean bean = ManagementFactory.getThreadMXBean();

    protected long getTimeInMilliseconds() {
        if (bean.isCurrentThreadCpuTimeSupported())
            return bean.getCurrentThreadCpuTime() / nanosecondsPerMillisecond;
        else
            return System.nanoTime() / nanosecondsPerMillisecond;
    }

    public void start(final String name) {
        put(name, new Interval(getTimeInMilliseconds()));
    }

    public void end(final String name) {
        get(name).setEnd(getTimeInMilliseconds());
    }

    public void outputTable(int width, PrintStream out) {
        // Heading
        for (final Entry<String, Interval> e : entrySet())
            out.print(Strings.padStart(e.getKey(), width, ' ') + " ");
        out.println();
        // Values
        for (final Entry<String, Interval> e : entrySet())
            out.print(Strings.padStart(e.getValue().toString(), width, ' ') + " ");
        out.println();
    }
}
