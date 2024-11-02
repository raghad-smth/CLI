package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import main.CLI;

public class touchTest {

    private CLI cli = new CLI(); 

    @Test
    public void testCreateNewFile() {
        String filePath = "testing.txt";
        String result = cli.touchFile(filePath);
        assertEquals("File created successfully.", result);

        // Clean up
        assertTrue(new File(filePath).delete());
    }

    @Test
    public void testUpdateTimestamp() {
        String filePath = "testing.txt";
        cli.touchFile(filePath); // Create the file first
        String result = cli.touchFile(filePath);
        assertEquals("File timestamp updated successfully.", result);

        // Clean up
        assertTrue(new File(filePath).delete());
    }
}

