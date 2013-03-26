package owl2vcs.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

public class CommonUtils {

    protected CommonUtils() {
    }

    public static String stackTrackAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static void messageBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void messageBox(String message) {
        messageBox(message, "Information");
    }

    public static void errorBox(String message) {
        JOptionPane.showMessageDialog(null, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
