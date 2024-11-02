package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import main.CLI;

public class mvTest {
    private CLI cli = new CLI();
    private Path tempSourceFile;
    private Path tempDestinationFile;

    @Before
    public void setUp() throws Exception {
       
        tempSourceFile = Files.createTempFile("tempSourceFile", ".txt");
        tempDestinationFile = Paths.get(tempSourceFile.getParent().toString(), "tempDestinationFile.txt");
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(tempSourceFile);
        Files.deleteIfExists(tempDestinationFile);
    }

    @Test
    public void testMoveFile_Success() {
        String result = cli.moveFile(tempSourceFile.toString(), tempDestinationFile.toString());
       assertEquals("File moved successfully to " + tempDestinationFile, result);
        assertFalse(Files.exists(tempSourceFile));
        assertTrue(Files.exists(tempDestinationFile));
    }

    @Test
    public void testMoveFile_SourceNotFound() {
        String nonExistentFilePath = tempSourceFile.getParent().resolve("nonExistentFile.txt").toString();
        String result = cli.moveFile(nonExistentFilePath, tempDestinationFile.toString());
        assertEquals("Error: Source file not found.", result);
    }

    @Test
    public void testMoveFile_DestinationExists() throws Exception {
        Files.createFile(tempDestinationFile); // Create destination file to test conflict
        String result =cli.moveFile(tempSourceFile.toString(), tempDestinationFile.toString());
        assertEquals("File moved successfully to " + tempDestinationFile, result); // Since we are allowing
                                                                                              // REPLACE_EXISTING
        assertFalse(Files.exists(tempSourceFile));
        assertTrue(Files.exists(tempDestinationFile));
    }
}
