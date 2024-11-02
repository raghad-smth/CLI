package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import main.CLI;

public class cdTest {

    private CLI cli = new CLI();
    private File testDir = new File(System.getProperty("user.dir"));

     @Test
    public void testCdValidDirectory() {
        String result = cli.cd("..");
        assertTrue(result.startsWith("Changed directory to:"));
        assertEquals(testDir.getAbsolutePath(), cli.pwd());
    }

    
    @Test
    public void testCdInvalidDirectory() {
        String result = cli.cd("invalid_directory");
        assertEquals("Error: Directory does not exist.", result);
    }

}
