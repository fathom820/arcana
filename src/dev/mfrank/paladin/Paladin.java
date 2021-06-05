/*
This is the Paladin command engine. Most of the game's commands don't take arguments, instead relying on
the pre-defined context to calculate the results. The engine is designed like this for the player's ease of use,
 */

package dev.mfrank.paladin;

// Java libraries
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

// Project modules
import dev.mfrank.Main;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.utils.FileEngine;

// Import global var player from main class
import static dev.mfrank.Main.player;


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

    private static String[] disabledCommands;


    // CONSTRUCTOR //
    public void init() {
        Debug.tell("Initializing Paladin...");


        // Command instantiation
        debug = new Command (
                "debug",
                "dbg",
                "Intended for developer use. Displays debug messages."
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
        disabledCommands = new String[] {};
    }

    // INTERPRETATIONS //
    public static boolean interpret(String cmd) throws IOException {


        /*
        // TODO: Not working properly.
        This section refers to the aliases.
        Let's say the user enters "h", which is an alias but would not be recognized by the next statement.
        In that case, it will be run through this algorithm, which will test if it is a valid alias;
        If so, change the variable to the rightful command.
         */
        if(aliasMap.containsValue(cmd)) {
            for (Entry<String,String> entry : aliasMap.entrySet()) {
                if (entry.getKey().equals(cmd)) {
                    cmd = entry.getKey();
                }
            }
        }

        /*
        If command is disabled in given context, then set it to "NULL" so that it is caught
        by the default section of the switch statement.
         */
        for (String token : disabledCommands) {
            if (cmd.equals(token)) {
                cmd = "NULL";
                break;
            }
        }

        switch (cmd) {
            default:
                return false;

            case "debug":
                Debug.setState(!Debug.getState());
                if (Debug.getState())
                    Io.tell("Debug messages: ON");
                else
                    Io.tell("Debug messages: OFF");
            break;

            case "version":
                Io.printVersion();
            break;

            case "credits":
                Debug.tell("Showing credits");
                Io.tell("Nothing here yet.");
            break;

            case "reset":
                Debug.tell("Resetting save file");
                Io.tell("Nothing here yet.");
            break;

            case "save":
                Debug.tell("Saving progress to file");
                FileEngine.saveMage(player);
            break;

            case "quit":
                Debug.tell("Quitting to main menu.");
                Main.gameRunning = false;
            break;

            case "help":
                Debug.tell("Displaying help menu");
                Io.printDivider(25);
                Io.tellRaw("List of all commands:");
                Io.setIndent(1);
                for (Command c : commandArray) {

                    Io.tellRaw(c.getKeyword() + ": " + c.getDescription());
                }
                Io.setIndent(0);
                Io.printDivider(25);
            break;
        }

        return true;
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
    public Command[] getCommandArray () {
        return Paladin.commandArray;
    }


    // SETTERS //
    public void setDisabledCommands (String[] disabledCommands) {
        Paladin.disabledCommands = disabledCommands;
    }
}