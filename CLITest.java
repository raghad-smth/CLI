import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {
    private CLI cli;

    @BeforeEach
    void setUp() {
        cli = new CLI();
    }

    @AfterEach
    void tearDown() {
        // Clean up files and directories after each test
        deleteTestFile("testFile.txt");
        deleteTestFile("testOutput.txt");
        deleteTestFile("inputTestFile.txt");
        deleteTestFile("appendTestFile.txt");
        deleteTestDirectory("testDir");
    }

    void deleteTestFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) file.delete();
    }

    void deleteTestDirectory(String dirName) {
        File dir = new File(dirName);
        if (dir.exists()) {
            File[] contents = dir.listFiles();
            if (contents != null) {
                for (File file : contents) file.delete();
            }
            dir.delete();
        }
    }

    @Test
    void testRmSingleFile() throws IOException {
        File file = new File("testFile.txt");
        file.createNewFile();
        assertTrue(file.exists());

        String result = cli.rm(new String[]{"testFile.txt"});
        assertEquals("Success: Files and/or directories deleted.", result);
        assertFalse(file.exists());
    }

    @Test
    void testRmDirectoryWithoutRecursiveFlag() {
        File dir = new File("testDir");
        dir.mkdir();
        assertTrue(dir.exists());

        String result = cli.rm(new String[]{"testDir"});
        assertEquals("Error: testDir is a directory. Use -r to remove directories.", result);
        assertTrue(dir.exists());
    }

    @Test
    void testRmDirectoryWithRecursiveFlag() {
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
    void testRmNonExistentFile() {
        String result = cli.rm(new String[]{"nonExistentFile.txt"});
        assertEquals("Error: nonExistentFile.txt does not exist.", result);
    }

    @Test
    void testRedirectToFile() throws IOException {
        String commandOutput = "Hello, World!";
        String fileName = "testOutput.txt";

        String result = cli.redirectTo(commandOutput, fileName);
        assertEquals("Success: Output redirected to " + fileName, result);

        String fileContent = Files.readString(Paths.get(fileName));
        assertEquals(commandOutput, fileContent);
    }

    @Test
    void testRedirectToFileOverwrite() throws IOException {
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

    @Test
    void testCatToFile() throws IOException {
        String inputFileName = "inputTestFile.txt";
        String testInput = "Line 1" + System.lineSeparator() + "Line 2" + System.lineSeparator() + "EOF" + System.lineSeparator();
        System.setIn(new java.io.ByteArrayInputStream(testInput.getBytes()));

        String result = cli.catToFile(inputFileName);
        assertEquals("Success: Input saved to " + inputFileName, result);

        // Expected content with correct line separators
        String expectedContent = "Line 1" + System.lineSeparator() + "Line 2" + System.lineSeparator();
        String fileContent = Files.readString(Paths.get(inputFileName));

        assertEquals(expectedContent, fileContent);
        System.setIn(System.in); // Reset standard input
    }

    @Test
    void testAppendToFileCreatesNewFile() throws IOException {
        String commandOutput = "First Line";
        String fileName = "appendTestFile.txt";

        String result = cli.appendToFile(commandOutput, fileName);
        assertEquals("Success: Output appended to " + fileName, result);

        String fileContent = Files.readString(Paths.get(fileName));
        assertEquals(commandOutput + System.lineSeparator(), fileContent);
    }

    @Test
    void testAppendToFileAppendsContent() throws IOException {
        String initialContent = "Initial Content";
        String appendContent = "Appended Content";
        String fileName = "appendTestFile.txt";

        cli.appendToFile(initialContent, fileName);
        String initialFileContent = Files.readString(Paths.get(fileName));
        assertEquals(initialContent + System.lineSeparator(), initialFileContent);

        cli.appendToFile(appendContent, fileName);
        String updatedFileContent = Files.readString(Paths.get(fileName));
        assertEquals(initialContent + System.lineSeparator() + appendContent + System.lineSeparator(), updatedFileContent);
    }


}
