package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.CLI;

public class catTest {
      
    private CLI cli = new CLI();
  
    @Test
    public void testCatToFile() throws IOException {
        String inputFileName = "inputTestFile.txt";
        String testInput = "Line 1" + System.lineSeparator() + "Line 2" + System.lineSeparator() + "EOF"
                + System.lineSeparator();
        System.setIn(new java.io.ByteArrayInputStream(testInput.getBytes()));

        String result = cli.catToFile(inputFileName);
        assertEquals("Success: Input saved to " + inputFileName, result);

        // Expected content with correct line separators
        String expectedContent = "Line 1" + System.lineSeparator() + "Line 2" + System.lineSeparator();
        String fileContent = Files.readString(Paths.get(inputFileName));

        assertEquals(expectedContent, fileContent);
        System.setIn(System.in);
    }
}
