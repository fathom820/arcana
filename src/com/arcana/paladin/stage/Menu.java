package com.arcana.paladin.stage;

import com.arcana.entity.Player;
import com.arcana.paladin.Command;
import com.arcana.paladin.Stage;
import com.arcana.utils.FileEngine;

import static com.arcana.Main.player;
import static com.arcana.Main.setPlayer;
import static com.arcana.Main.gameRunning;

import java.io.IOException;
import java.util.Scanner;

public class Menu extends Stage {

    private static Command newMage;
    private static Command loadMage;
    private static Command listMages;

    //private static Command[] localCommands;

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

        super.addCommand(newMage);
        super.addCommand(loadMage);
        super.addCommand(listMages);

        //localCommands = new Command[] {newMage, loadMage, listMages};
    }


    /*
    This segment interprets the command passed by the end user.
    First the command is run through the base, then it is run through the
    stage-specific switch statement.
     */
    public boolean interpret(String cmd) throws IOException {
        if (!super.interpret(cmd)) {

            switch (cmd) {
                default:
                    super.tellInvalidCmd();
                    return false;

                case "new":
                    System.out.print("Enter the name of your Mage: ");
                    Scanner kb = new Scanner(System.in);
                    setPlayer(new Player(kb.nextLine()));
                    FileEngine.configure(player.getName());
                    gameRunning = FileEngine.newFile(player.getName());
                    FileEngine.saveFile(player);
                    // FileEngine.loadFile();
                break;

                case "load":
                    // TODO: load file
                break;

                case "list":
                    // TODO: list of all mages in directory

            }
            return true;
        }
        return false;
    }
}
