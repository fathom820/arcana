/*
This is the Paladin command engine. Most of the game's commands don't take arguments, instead relying on
the pre-defined context to calculate the results. The engine is designed like this for the player's ease of use,
 */

package com.arcana.input;

import com.arcana.Main;
import com.arcana.config.Debug;
import com.arcana.utils.Text;

import java.util.HashMap;
import java.util.Map.Entry;

public class Input {

    private static final Command debug = new Command("debug", "dbg", "Intended for developer use. Displays debug messages.");
    private static final Command version = new Command("version", "ver", "Displays the game's current version.");
    private static final Command credits = new Command("credits", "cred", "Displays the game's credits.");
    private static final Command reset = new Command("reset", "r", "Resets the current mage to the beginning of the game.");
    private static final Command save = new Command("save", "s", "Saves the current mage's progress.");
    private static final Command quit = new Command("quit", "q", "Quits to main menu. Does not auto-save.");
    private static final Command help = new Command("help", "h", "Displays list of commands.");
    private static final Command[] commandArray = new Command[] {
            debug,
            version,
            credits,
            reset,
            save,
            quit,
            help
    };

    private static HashMap<String, String> aliasMap = new HashMap<>();       // map alias to keyword


    // CONSTRUCTOR
    public static void init() {
        Debug.tell("test");
        for (Command c : commandArray) {
            aliasMap.put(c.getKeyword(), c.getAlias());
        }
    }

    // INTERPRETATIONS //
    public static void interpret(String cmd) {

        /*
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

        switch (cmd) {
            default:
                Debug.warn("Invalid command. Use \"help\" to see a list of valid commands.");
            break;

            case "debug":
                Debug.toggle();
                if (Debug.getStatus())
                    Debug.warn("Debug messages: OFF");
                else
                    Debug.warn("Debug messages: ON");
            break;

            case "version":
                Text.printVersion();
            break;

            case "credits":
                Debug.tell("Showing credits");
                Debug.warn("Nothing here yet.");
            break;

            case "reset":
                Debug.tell("Resetting save file");
                Debug.warn("Nothing here yet.");
            break;

            case "save":
                Debug.tell("Saving progress to file");
                Debug.warn("Nothing here yet.");
            break;

            case "quit":
                Debug.tell("Quitting to main menu.");
                Main.gameRunning = false;
            break;

            case "help":
                Debug.tell("Displaying help menu");
                for (Command c : commandArray) {
                    Debug.warn(c.getKeyword() + ": " + c.getDescription());
                }
            break;
        }
    }
}