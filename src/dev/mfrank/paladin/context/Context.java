package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.engine.EngineFile;

import java.io.IOException;

import static dev.mfrank.Main.gameRunning;

public class Context {

    private Command[] localCommands;
    private String[] disabledCommands;
    private Command[] globalCommands;

    private Command debug, version, quit, help;

    public Context () {
        debug = new Command (
            "debug",
            "dbg",
            "Displays debug info and disables thread delay."
        );
        version = new Command (
            "version",
            "v",
            "Displays the game's current version."
        );
        quit = new Command (
            "quit",
            "q",
            "Quits to main menu, or quits the game."
        );
        help = new Command (
            "help",
            "h",
            "Displays all usable commands."
        );

        globalCommands = new Command[] {
            debug, version, quit, help
        };

        localCommands = new Command[0];
        disabledCommands = new String[0];
    }


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
                Debug.tell("set to null");
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



            case "save":
                Debug.tell("Saving progress to file");
                EngineFile.saveMage(Main.getPlayer());
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

                // display global commands
                Io.setIndent(1);
                Io.tellRaw("GLOBAL");
                Io.setIndent(2);
                parseCommand(globalCommands);

                // display local commands
                Io.setIndent(1);
                Io.tellRaw("LOCAL");
                Io.setIndent(2);
                parseCommand(localCommands);
                Io.printDivider();
                Io.setIndent(0);
                break;
        }

        return true;
    }

    private void parseCommand(Command[] localCommands) throws InterruptedException {
        for (Command c : localCommands) {
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
    }

    public void addCommand (Command cmd) {
        Command[] temp = new Command[localCommands.length + 1];
        System.arraycopy(localCommands, 0, temp, 0, localCommands.length);
        temp[temp.length - 1] = cmd;
        localCommands = temp;
    }

    public static void tellInvalidCmd() {
        Io.tell("Invalid command. Use \"help\" to see a list of valid commands.");
    }



    // GETTERS //

    // SETTERS//
    public void setDisabledCommands (String[] disabledCommands) { this.disabledCommands = disabledCommands;
    }

    public void setLocalCommands(Command[] localCommands) {
        this.localCommands = localCommands;
    }
}
