package owl2vcs;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;


public class DiffIT {

    @Test
    public void test() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String cmd = "java -cp target/owl2vcs.jar owl2vcs.tools.Diff";
        Process process = runtime.exec(cmd);
        InputStreamReader isr = new InputStreamReader(process.getErrorStream());
        BufferedReader br = new BufferedReader(isr);
        Boolean containsName = false;
        Boolean containsUsage = false;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if (line.contains("owl2diff"))
                containsName = true;
            if (line.contains("Usage"))
                containsUsage = true;
        }
        assertTrue(containsName);
        assertTrue(containsUsage);
    }
}
