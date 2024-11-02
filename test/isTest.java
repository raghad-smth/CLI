package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import main.CLI;

public class isTest {

    private CLI cli = new CLI();
    private File testDir = new File(System.getProperty("user.dir"));
    @Test
    public void testLs() throws IOException {
        // Create files for testing
        new File(testDir, "file1.txt").createNewFile();
        new File(testDir, "file2.txt").createNewFile();
        
        String contents = cli.ls();
        assertTrue(contents.contains("file1.txt"));
        assertTrue(contents.contains("file2.txt"));
    }


    @Test
    public void testLsReverseOrder() throws IOException {
        // Create files for testing
        new File(testDir, "b_file.txt").createNewFile();
        new File(testDir, "a_file.txt").createNewFile();
        
        String contents = cli.ls("-r");
        String[] lines = contents.split("\n");
        
        assertEquals(8, lines.length);
        assertEquals("visibleFile.txt", lines[0]);
        assertEquals("src", lines[1]);
    }

    @Test
    public void testLsWithBothOptions() throws IOException {
        // Create files for testing
        new File(testDir, ".hiddenFile").createNewFile();
        new File(testDir, "visibleFile.txt").createNewFile();
        
        String contents = cli.ls("-a", "-r");
        assertTrue(contents.contains(".hiddenFile"));
        assertTrue(contents.contains("visibleFile.txt"));
        
        String[] lines = contents.split("\n");
        assertEquals(10, lines.length);
        assertEquals("visibleFile.txt", lines[0]);
        assertEquals("src", lines[1]);
    }

}
