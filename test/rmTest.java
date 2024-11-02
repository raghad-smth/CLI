package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import main.CLI;

public class rmTest {
    private CLI cli = new CLI();
    private String testFileName;
     @Before
   public void setUp() {
        cli = new CLI();
        testFileName = "testfile.txt";
    }

    @After
    public void tearDown() {
        // Clean up files created during the tests
        deleteTestFile("testFile.txt");
        deleteTestFile("testOutput.txt");
        deleteTestFile("inputTestFile.txt");
        deleteTestFile("appendTestFile.txt");
        deleteTestDirectory("testDir");

        // Delete the main test file
        deleteTestFile(testFileName);
    }

    public void deleteTestFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public void deleteTestDirectory(String dirName) {
        File dir = new File(dirName);
        if (dir.exists()) {
            File[] contents = dir.listFiles();
            if (contents != null) {
                for (File file : contents) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    @Test
    public void testRmSingleFile() throws IOException {
        File file = new File("testFile.txt");
        file.createNewFile();
        assertTrue(file.exists());

        String result = cli.rm(new String[]{"testFile.txt"});
        assertEquals("Success: Files and/or directories deleted.", result);
        assertFalse(file.exists());
    }

    @Test
    public  void testRmDirectoryWithoutRecursiveFlag() {
        File dir = new File("testDir");
        dir.mkdir();
        assertTrue(dir.exists());

        String result = cli.rm(new String[]{"testDir"});
        assertEquals("Error: testDir is a directory. Use -r to remove directories.", result);
        assertTrue(dir.exists());
    }

    @Test
    public  void testRmDirectoryWithRecursiveFlag() {
        File dir = new File("testDir");
        dir.mkdir();
        File subFile = new File(dir, "subFile.txt");
        try {
            subFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(dir.exists());
        assertTrue(subFile.exists());

        String result = cli.rm(new String[]{"-r", "testDir"});
        assertEquals("Success: Files and/or directories deleted.", result);
        assertFalse(dir.exists());
    }

    @Test
    public void testRmNonExistentFile() {
        String result = cli.rm(new String[]{"nonExistentFile.txt"});
        assertEquals("Error: nonExistentFile.txt does not exist.", result);
    }

}
