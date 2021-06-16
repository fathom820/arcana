package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.entity.Mage;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.engine.EngineFile;

import java.io.IOException;

import static dev.mfrank.Main.*;

public class ContextMenu extends Context {

    private static Command newMage, loadMage, listMages, deleteMage, reset, save, quit;


    public ContextMenu() {

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
                    tellInvalidCmd();
                    return false;

                case "new":
                    Io.tell("You've decided to create a new mage. Wonderful!");
                    setPlayer(new Mage(Io.prompt("Name")));
                    EngineFile.setCurrentMage(getPlayer().getName());
                    gameRunning = EngineFile.newMage(getPlayer().getName());
                    EngineFile.saveMage(getPlayer());
                break;

                case "load":
                    Main.setPlayer(EngineFile.loadMage());
                break;

                case "list":
                    EngineFile.listMages();
                break;

                case "delete":
                    EngineFile.listMages();
                    EngineFile.deleteMage();
            }
            return true;
        }
        return false;
    }
}
