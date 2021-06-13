package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.entity.Mage;
import dev.mfrank.paladin.Command;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.engine.FileEngine;

import java.io.IOException;

import static dev.mfrank.Main.*;

public class ContextMenu extends Context {

    private static Command newMage;
    private static Command loadMage;
    private static Command listMages;
    private static Command deleteMage;


    public ContextMenu() {
        /*
        Saving and resetting are disabled in the main menu, because
        those commands are meant to be passed during gameplay.
         */
        super.init();
        super.setDisabledCommands( new String[] {
            "save",
            "reset"
        });

        newMage = new Command (
                "new",
                "n",
                "Create a new mage."
        );
        loadMage = new Command (
                "load",
                "ld",
                "Load an existing mage."
        );
        listMages = new Command (
                "list",
                "li",
                "Display a list of all current mages."
        );
        deleteMage = new Command (
                "delete",
                "del",
                "Deletes a mage."
        );

        super.addCommand(newMage);
        super.addCommand(loadMage);
        super.addCommand(listMages);
        super.addCommand(deleteMage);
    }


    /*
    This segment interprets the command passed by the end user.
    First the command is run through the base, then it is run through the
    context-specific switch statement.
     */
    public boolean interpret(String cmd) throws IOException, InterruptedException {
        if (!super.interpret(cmd)) {

            switch (cmd) {
                default:
                    Paladin.tellInvalidCmd();
                    return false;

                case "new":
                    Io.tell("You've decided to create a new mage. Wonderful!");
                    setPlayer(new Mage(Io.prompt("Name")));
                    FileEngine.setCurrentMage(getPlayer().getName());
                    gameRunning = FileEngine.newMage(getPlayer().getName());
                    FileEngine.saveMage(getPlayer());
                    // FileEngine.loadFile();
                break;

                case "load":
                    Main.setPlayer(FileEngine.loadMage());
                break;

                case "list":
                    FileEngine.listMages();
                break;

                case "delete":

                    FileEngine.listMages();
                    FileEngine.deleteMage();
            }
            return true;
        }
        return false;
    }
}
