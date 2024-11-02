package test;
// src/MainTest.java
import static org.junit.Assert.*;



import org.junit.Test; // Ensure this is correct

import main.CLI;

// java -cp "libs/*;bin" org.junit.runner.JUnitCore MainTest
public class Is_aTest {


    // Testing when the command have been enterd correctly 
    @Test 
    public void Is_aCorrect() {
        CLI cli = new CLI();
        String excpected = "Files and directories in the current directory:" + System.lineSeparator() +
                          ".vscode" + System.lineSeparator() +
                          "bin" + System.lineSeparator() +
                          "libs" + System.lineSeparator() +
                          "src" + System.lineSeparator();
          
        @SuppressWarnings("static-access")
        String result = cli.Is_a("Is-a");
        assertEquals(excpected ,result);
    }


    // Testing if the command has been mis spelled 
    @Test 
    public void Is_aMissSpelled(){
        String expected = "Did you mean Is-a?";
                      
        String result = CLI.Is_a("I-");
        assertEquals(expected, result);

        String result1 = CLI.Is_a("is-");
        assertEquals(expected, result1);

        String result2 = CLI.Is_a("Is a");
        assertEquals(expected, result2);
    }
      
}
