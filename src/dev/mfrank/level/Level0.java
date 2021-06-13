package dev.mfrank.level;

import dev.mfrank.Main;
import dev.mfrank.paladin.context.ContextMenu;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

import java.io.IOException;

public class Level0 extends Level {

    private ContextMenu menu;

    public Level0 () {

        menu = new ContextMenu();

        super.setId(0);
        super.setName(
            "*--------------------------------------------------------------------------*\n" +
            "|    ▄████████    ▄████████  ▄████████    ▄████████ ███▄▄▄▄      ▄████████ |\n" +
            "|   ███    ███   ███    ███ ███    ███   ███    ███ ███▀▀▀██▄   ███    ███ |\n" +
            "|   ███    ███   ███    ███ ███    █▀    ███    ███ ███   ███   ███    ███ |\n" +
            "|   ███    ███  ▄███▄▄▄▄██▀ ███          ███    ███ ███   ███   ███    ███ |\n" +
            "| ▀███████████ ▀▀███▀▀▀▀▀   ███        ▀███████████ ███   ███ ▀███████████ |\n" +
            "|   ███    ███ ▀███████████ ███    █▄    ███    ███ ███   ███   ███    ███ |\n" +
            "|   ███    ███   ███    ███ ███    ███   ███    ███ ███   ███   ███    ███ |\n" +
            "|   ███    █▀    ███    ███ ████████▀    ███    █▀   ▀█   █▀    ███    █▀  |\n" +
            "*--------------------------------------------------------------------------*\n"
        );
        super.setInfo (
            "Welcome to Arcana, a turn-based combat game with a dash of magic!\n\n" +
            "Seeking to obtain your Mage's License from the Arcane Guild, you descend\n" +
            "into a dungeon, where you must descend to the very bottom by defeating\n" +
            "waves of enemies. Your library of spells will grow from scrolls carried by\n" +
            "enemies -- put them to good use!"
        );
    }

    public void run () throws IOException, InterruptedException {
        Debug.tell("Begin lvl0");
        printWelcome();

        while(!Main.getGameRunning()) {
            menu.interpret(Io.in());
        }

        Debug.tell("Game start");
        Main.getPlayer().getCurrentLevel().run();
    }

    public void printWelcome() throws InterruptedException {

        if (Main.enableConsole) {
            Io.delayType(getName(), 1);
        }

        else {
            Io.tellRaw(getName());
        }
        Io.tellRaw(getInfo());
        Io.printDivider();

    }

}
