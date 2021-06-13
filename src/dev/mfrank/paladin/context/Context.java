package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.paladin.Command;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.engine.FileEngine;

import java.io.IOException;

import static dev.mfrank.Main.gameRunning;

public abstract class Context extends Paladin {
    private static String[] disabledCommands;

    public boolean interpret(String cmd) throws IOException, InterruptedException {


        /*
        // TODO: Not working properly.
        This section refers to the aliases.
        Let's say the user enters "h", which is an alias but would not be recognized by the next statement.
        In that case, it will be run through this algorithm, which will test if it is a valid alias;
        If so, change the variable to the rightful command.
         */
        /*
        if(super.aliasMap.containsValue(cmd)) {
            for (Map.Entry<String,String> entry : aliasMap.entrySet()) {
                if (entry.getKey().equals(cmd)) {
                    cmd = entry.getKey();
                }
            }
        }
        */


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
                    Io.tell("Debug mode: ON");
                else
                    Io.tell("Debug mode: OFF");
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
                FileEngine.saveMage(Main.getPlayer());
                break;

            case "quit":
                if (gameRunning) {
                    Debug.tell("Quitting to main menu.");
                    Main.gameRunning = false;
                }

                break;

            case "help":
                Debug.tell("Displaying help menu");
                Io.printDivider();
                Io.tellRaw("List of all commands:");
                Io.setIndent(1);
                for (Command c : getCommandArray()) {
                    boolean disabled = false;
                    for (String token : disabledCommands) {
                        if (c.getKeyword().equals(token)) {
                            disabled = true;
                        }
                    }
                    if (!disabled) {
                        Io.tellRaw(c.getKeyword() + ": " + c.getDescription());
                        Thread.sleep(20);
                    }
                }
                Io.setIndent(0);
                Io.printDivider();
                break;
        }

        return true;
    }

    // SETTERS
    public void setDisabledCommands (String[] disabledCommands) {
        Context.disabledCommands = disabledCommands;
    }
}
