/*
This is the Paladin command engine. Most of the game's commands don't take arguments, instead relying on
the pre-defined context to calculate the results. The engine is designed like this for the player's ease of use,
 */

package dev.mfrank.paladin;

// Java libraries
import java.util.HashMap;

// Project modules
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

// Import global var player from main class


public class Paladin {

    private static Command debug;
    private static Command version;
    private static Command credits;
    private static Command reset;
    private static Command save;
    private static Command quit;
    private static Command help;


    private static Command[] commandArray;

    private static HashMap<String, String> aliasMap = new HashMap<>();       // map alias to keyword

    // private static String[] disabledCommands;


    // CONSTRUCTOR //
    public void init() {
        Debug.tell("Initializing Paladin...");

        // Command instantiation
        debug = new Command (
                "debug",
                "dbg",
                "[DEV USE] Enables engine logs and disables thread delay."
        );
        version = new Command (
                "version",
                "ver",
                "Displays the game's current version."
        );
        credits = new Command (
                "credits",
                "cred",
                "Displays the game's credits."
        );
        reset = new Command (
                "reset",
                "r",
                "Resets the current mage to the beginning of the game."
        );
        save = new Command (
                "save",
                "s",
                "Saves the current mage's progress."
        );
        quit = new Command (
                "quit",
                "q",
                "Quits to main menu. Does not auto-save."
        );
        help = new Command (
                "help",
                "h",
                "Displays list of commands."
        );


        commandArray = new Command[] {
                debug,
                version,
                credits,
                reset,
                save,
                quit,
                help
        };

        for (Command c : commandArray) {
            aliasMap.put(c.getKeyword(), c.getAlias());
        }

        // Initialize disabled commands
        // disabledCommands = new String[] {};
    }

    // OTHER //
    /*
    Add command to commandArray. Is intended to be used by child classes, not the parent class.
     */
    public void addCommand (Command cmd) {
        Command[] temp = new Command[commandArray.length + 1];
        System.arraycopy(commandArray, 0, temp, 0, commandArray.length);
        temp[temp.length - 1] = cmd;
        commandArray = temp;
    }

    // Tells end user that their command was not valid.
    public static void tellInvalidCmd() {
        Io.tell("Invalid command. Use \"help\" to see a list of valid commands.");
    }

    // GETTERS //
    public static Command[] getCommandArray () {
        return Paladin.commandArray;
    }


    // SETTERS //

}