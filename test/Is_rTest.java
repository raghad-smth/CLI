package test;

import static org.junit.Assert.assertEquals;



import org.junit.Test;

import main.CLI;

public class Is_rTest {
    

    // Testing if the user doesn't specify a directory, the current disrectory should be used
    @Test 
    public void NoDir() {
           CLI cli = new CLI();
           String expected = "Files and directories in the specified directory in reverse:\n" +
           "src\n" +
           "libs\n" +
           "bin\n" +
           ".vscode\n";
                      
        @SuppressWarnings("static-access")
        String result = cli.ls_r("Is-r","");
        assertEquals(expected, result); 
        };


    // Testing against not valid directories 
    @Test 
    public void notValidDir(){
        CLI cli = new CLI();
        String expected = "Directory doesn't exist.";
                   
     @SuppressWarnings("static-access")
     String result = cli.ls_r("Is-r","/user/home.people");
     assertEquals(expected, result); 
        
    }


    // Testing with a valid directory 
    @Test
    public void validDir() {
        CLI cli = new CLI();
        String expected = "Files and directories in the specified directory in reverse:\n" +
        "main.exe\n" +
        "main.cpp\n" +
        "AVL.h\n" +
        "AVL.cpp\n";
         
     @SuppressWarnings("static-access")
     String result = cli.ls_r("Is-r","C:\\Users\\\\raghad\\OneDrive\\Desktop\\AVL-Tree");
     assertEquals(expected, result); 
     };

    // A Probably misspelled test 
    @Test 
    public void missspelld(){
        CLI cli = new CLI();
        String expected = "Did you mean `Is-r`?";
                   
     @SuppressWarnings("static-access")
     String result = cli.ls_r("Is-","");
     assertEquals(expected, result); 
        
    }


}
