package test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import main.CLI;

public class mkdirTest {

    //Testing if user didn't provid a directory name 
    @Test
    public void emptyDir() {

        CLI cli = new CLI();
       
        @SuppressWarnings("static-access")
        String result = cli.mkdir("mkdir","");

        assertEquals("Directory name must be provided", result);

    }


    //Testing if the input directory already exist 
    @Test
    public void alreadyExistDir() {

        CLI cli = new CLI();
        String newDirectory  = "C:\\Users\\raghad\\OneDrive\\Desktop";
        File directory = new File(newDirectory);
       
        @SuppressWarnings("static-access")
        String result = cli.mkdir("mkdir",newDirectory);

        assertEquals("Directory already exists at: "+ directory.getAbsolutePath() + "\n", result);

    }


    // Test creating a successfull creation for a new directory 
     @Test
    public void creationSuccess() {

        CLI cli = new CLI();
        String newDirectory  = "C:\\Users\\raghad\\OneDrive\\Desktop\\pretty";
        File directory = new File(newDirectory);
        if (directory.exists()) {
            directory.delete();
        }
        @SuppressWarnings("static-access")
        String result = cli.mkdir("mkdir",newDirectory);

        assertEquals("Directory successfully created: " + directory.getName() + "\n" +
                "Absolute path: " +directory.getAbsolutePath() + "\n", result);

        // Clean up: Delete the created directory after testing
        directory.delete();
    }




    //Test for a misspelled probabilty 
    @Test
    public void missspelld() {
        // Use a directory with a non-existent parent to simulate failure
        CLI cli = new CLI();
        String directory = "nonExistentParent/newDirectory";

        @SuppressWarnings("static-access")
        String result = cli.mkdir("mkd", directory);

        assertEquals("Did you mean mkdir?\n", result);
    }  
}
