package test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import main.CLI;

public class rmdirTest {
 
    CLI cli = new CLI();
    private Path testDir;
    private Path nonEmptyDir;
    private Path nonExistentDir;

    @Before
    public void setUp() throws IOException {
        
       
        // Create temporary directories for testing
        testDir = Files.createTempDirectory("emptyDir");
        nonEmptyDir = Files.createTempDirectory("nonEmptyDir");
        Files.createFile(nonEmptyDir.resolve("fileInDir.txt")); // Add file to non-empty directory
        nonExistentDir = Paths.get("nonExistentDir");
    }

    @After
    public void tearDown() throws IOException {
        // Clean up test directories
        if (Files.exists(testDir)) {
            Files.deleteIfExists(testDir);
        }
        if (Files.exists(nonEmptyDir)) {
            Files.walk(nonEmptyDir)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> path.toFile().delete());
        }
    }
    //---Test Dir is Empty to remove
    @Test
    public void testRemoveEmptyDirectory() {
     
        @SuppressWarnings("static-access")
        String result = cli.removeDirectory(testDir.toString());
        assertEquals("Directory removed successfully.", result);
        //assertFalse(Files.exists(testDir), "Directory should be deleted.");
    }
    //---Test Dir NonExistent
    @SuppressWarnings("static-access")
    @Test
    public void testRemoveNonExistentDirectory() {
    
        String result = cli.removeDirectory(nonExistentDir.toString());
        assertEquals("Error: Directory does not exist.", result);
    }
    //---Test if Dir is Empty for remove
    @SuppressWarnings("static-access")
    @Test
    public void testRemoveNonEmptyDirectory() {
        String result = cli.removeDirectory(nonEmptyDir.toString());
        assertEquals("Error: Directory is not empty.", result);
      // assertTrue(Files.exists(nonEmptyDir), "Non-empty directory should not be deleted.");
    }
}

