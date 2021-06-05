package dev.mfrank.paladin.context;

import dev.mfrank.entity.Mage;
import dev.mfrank.paladin.Command;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.utils.FileEngine;

import static dev.mfrank.Main.player;
import static dev.mfrank.Main.setPlayer;
import static dev.mfrank.Main.gameRunning;

import java.io.IOException;
import java.util.Scanner;

public class Menu extends Paladin {

    private static Command newMage;
    private static Command loadMage;
    private static Command listMages;
    private static Command deleteMage;


    public Menu () {
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
    public static boolean interpret(String cmd) throws IOException {
        if (!Paladin.interpret(cmd)) {

            switch (cmd) {
                default:
                    Paladin.tellInvalidCmd();
                    return false;

                case "new":
                    Io.tell("Enter the name of your new mage.");
                    setPlayer(new Mage(Io.in()));
                    FileEngine.setCurrentMage(player.getName());
                    gameRunning = FileEngine.newMage(player.getName());
                    FileEngine.saveMage(player);
                    // FileEngine.loadFile();
                break;

                case "load":
                    player = FileEngine.loadMage();
                break;

                case "list":
                    FileEngine.listMages();
                break;

                case "delete":
                    Io.tell("Which mage would you like to delete?");
                    FileEngine.listMages();
                    FileEngine.deleteMage();
            }
            return true;
        }
        return false;
    }
}
