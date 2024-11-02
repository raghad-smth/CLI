package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.CLI;

public class redirectTest {
    private CLI cli = new CLI();
    
    private Path testDir;
    private Path nonEmptyDir;

   

    @Before
    public void setUp() throws IOException {
        
       
        // Create temporary directories for testing
        testDir = Files.createTempDirectory("emptyDir");
        nonEmptyDir = Files.createTempDirectory("nonEmptyDir");
        Files.createFile(nonEmptyDir.resolve("fileInDir.txt")); // Add file to non-empty directory
        Paths.get("nonExistentDir");
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

    @Test
    public void testRedirectToFile() throws IOException {
        String commandOutput = "Hello, World!";
        String fileName = "testOutput.txt";

        String result = cli.redirectTo(commandOutput, fileName);
        assertEquals("Success: Output redirected to " + fileName, result);

        String fileContent = Files.readString(Paths.get(fileName));
        assertEquals(commandOutput, fileContent);
    }

    @Test
    public void testRedirectToFileOverwrite() throws IOException {
        String initialOutput = "Initial Content";
        String updatedOutput = "Updated Content";
        String fileName = "testOutput.txt";

        cli.redirectTo(initialOutput, fileName);
        String initialContent = Files.readString(Paths.get(fileName));
        assertEquals(initialOutput, initialContent);

        cli.redirectTo(updatedOutput, fileName);
        String updatedContent = Files.readString(Paths.get(fileName));
        assertEquals(updatedOutput, updatedContent);
    }
}
