package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import main.CLI;

public class appendToFileTest {
    private CLI cli = new CLI();
    private String testFileName = "testfile.txt";;
    
    @Test
    public void testAppendToFileSuccess() {
        // Given
        String command = "Test command";

        // When
        String result = cli.appendToFile(command, testFileName);

        // Then
        assertEquals("Success!" + testFileName, result);
        // Check if the command is appended to the file
        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line = reader.readLine();
            assertEquals("Test command", line);
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testAppendToFileIOException() {
        // Given
        String command = "Test command";
        String invalidFileName = "/invalid_path/testfile.txt"; // Using an invalid path

        // When
        String result = cli.appendToFile(command, invalidFileName);

        // Then
        assertTrue(result.startsWith("Error! Unable to write to file ")); // Check for error message
        
    }
}
