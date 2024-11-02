package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.CLI;

public class helpTest {
    
    private CLI cli = new CLI();
    
    // Testing the help command
     @Test 
    public void validReturn(){
        
        String expected = "GNU bash, version 5.0.17(1)-release (x86_64-pc-linux-gnu)\n" +
        "These shell commands are defined internally. Type help' to see this list.\n" +
        "Type help name' to find out more about the function name'.\n" +
        "\n" +
        "  alias    bg       bind      break   builtin   caller  cd        command  compgen\n" +
        "  complete compopt  continue  declare dirs      disown  echo      enable   eval\n" +
        "  exec     exit     export    false   fc        fg      getopts   hash     help\n" +
        "  history  jobs     kill      let     local     logout  mapfile  popd     printf\n" +
        "  pushd    pwd      read      readarray readonly return  set      shift    shopt\n" +
        "  source   suspend  test      times   trap      true    type     typeset  ulimit\n" +
        "  umask    unalias  unset     wait    while\n" +
        "\n" +
        "Type help followed by a built-in command name to display information about that command.";
                   
     @SuppressWarnings("static-access")
     String result = cli.help("help", "");
     assertEquals(expected, result); 
    }

    //Testing against help commands that don't exist 
    @Test 
    public void notValidHelpCommand(){
      
        String expected = "No help available for: serve" ;
                   
     @SuppressWarnings("static-access")
     String result = cli.help("help", "serve");
     assertEquals(expected, result); 
    }


    // Testing the help commands 
    @Test
    public void testCdCommand() {
        String expected = "cd: cd [dir] Change the shell working directory.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "cd");
        assertEquals(expected, result);
    }

    @Test
    public void testAliasCommand() {
        String expected = "alias: alias [name[=value] ... ]Define or display aliases.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "alias");
        assertEquals(expected, result);
    }

    @Test
    public void testBgCommand() {
        String expected = "bg: bg [job_spec ... ] Resume suspended jobs in the background.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "bg");
        assertEquals(expected, result);
    }

    @Test
    public void testBindCommand() {
        String expected = "bind: bind [-lpvsPVS] [-m keymap] [-f file] [-q name] [-u function] [-r keyseq] Display or modify key bindings.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "bind");
        assertEquals(expected, result);
    }

    @Test
    public void testBreakCommand() {
        String expected = "break: break [n] Exit from within a for, while, or until loop.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "break");
        assertEquals(expected, result);
    }

    @Test
    public void testBuiltinCommand() {
        String expected = "builtin: builtin [shell-builtin [arg ...]]  Run a shell builtin, passing it arguments.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "builtin");
        assertEquals(expected, result);
    }

    @Test
    public void testCallerCommand() {
        String expected = "caller: caller [expr] Return the context of the current subroutine call.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "caller");
        assertEquals(expected, result);
    }

    @Test
    public void testCommandCommand() {
        String expected = "command: command [-pVv] command [arg ...]  Run a command without applying aliases.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "command");
        assertEquals(expected, result);
    }

    @Test
    public void testCompgenCommand() {
        String expected = "compgen: compgen [option] [word] Generate possible completion matches.";    
        @SuppressWarnings("static-access")
        String result = cli.help("help", "compgen");
        assertEquals(expected, result);
    }

    @Test
    public void testCompleteCommand() {
        String expected = "complete: complete [-abcdefgjksuv] [-pr] [-DE] [-o option] [-A action] [-G glob] [-W wordlist]  Specify how arguments are to be completed by the readline library.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "complete");
        assertEquals(expected, result);
    }

    @Test
    public void testContinueCommand() {
        String expected = "continue: continue [n] Resume the next iteration of a for, while, or until loop.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "continue");
        assertEquals(expected, result);
    }

    @Test
    public void testDeclareCommand() {
        String expected = "declare: declare [-aAfFgilnrtux] [-p] [name[=value] ...] Declare variables and give them attributes.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "declare");
        assertEquals(expected, result);
    }

    @Test
    public void testDirsCommand() {
        String expected = "dirs: dirs [-clpv] [+N] [-N] Display directory stack.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "dirs");
        assertEquals(expected, result);
    }

    @Test
    public void testDisownCommand() {
        String expected = "disown: disown [-h] [-ar] [jobspec ...] Remove jobs from the job table.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "disown");
        assertEquals(expected, result);
    }

    @Test
    public void testEchoCommand() {
        String expected = "echo: echo [arg ...] Write arguments to standard output.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "echo");
        assertEquals(expected, result);
    }

    @Test
    public void testEnableCommand() {
        String expected = "enable: enable [-a] [-dnps] [-f filename] [name ...]Enable and disable shell builtins.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "enable");
        assertEquals(expected, result);
    }

    @Test
    public void testExitCommand() {
        String expected = "exit: exit [n]Exit the shell.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "exit");
        assertEquals(expected, result);
    }

    @Test
    public void testExportCommand() {
        String expected = "export: export [-fn] [name[=value] ...] or export -p Set export attribute for variables and functions.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "export");
        assertEquals(expected, result);
    }

    @Test
    public void testFalseCommand() {
        String expected = "false: false Do nothing, unsuccessfully.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "false");
        assertEquals(expected, result);
    }

    @Test
    public void testFgCommand() {
        String expected = "fg: fg [job_spec] Place a job in the foreground.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "fg");
        assertEquals(expected, result);
    }

    @Test
    public void testGetoptsCommand() {
        String expected = "getopts: getopts optstring name [args] Parse positional parameters.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "getopts");
        assertEquals(expected, result);
    }

    @Test
    public void testHashCommand() {
        String expected = "hash: hash [-lr] [-p pathname] [-dt] [name ...] Remember or display program locations.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "hash");
        assertEquals(expected, result);
    }

    @Test
    public void testHelpCommand() {
        String expected = "help: help [-dms] [pattern ...] Display information about built-in commands.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "help");
        assertEquals(expected, result);
    }

    @Test
    public void testHistoryCommand() {
        String expected = "history: history [-c] [-d offset] [n] or history -anrw [filename] or history -ps arg [arg ...] Display or manipulate the history list.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "history");
        assertEquals(expected, result);
    }

    @Test
    public void testJobsCommand() {
        String expected = "jobs: jobs [-lnprs] [jobspec ...] or jobs -x command [args] Display status of jobs.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "jobs");
        assertEquals(expected, result);
    }

    @Test
    public void testKillCommand() {
        String expected = "kill: kill [-s sigspec | -n signum | -sigspec] [pid | jobspec ...] or kill -l [sigspec] Send a signal to a job.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "kill");
        assertEquals(expected, result);
    }

    @Test
    public void testLetCommand() {
        String expected = "let: let arg [arg ...]  Evaluate arithmetic expressions.";
        @SuppressWarnings("static-access")
        String result = cli.help("help", "let");
        assertEquals(expected, result);
    }
}




    


