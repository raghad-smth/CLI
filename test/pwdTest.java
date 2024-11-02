package test;

import static org.junit.Assert.assertEquals;


import java.io.File;

import org.junit.Test;

import main.CLI;

public class pwdTest {

    private CLI cli = new CLI();
    private File testDir = new File(System.getProperty("user.dir"));;

    @Test
    public void testPwd() {
        String currentDir = cli.pwd();
        assertEquals(testDir.getAbsolutePath(), currentDir);
    }

}
