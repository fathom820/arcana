package dev.mfrank;

// Java libraries
import java.awt.*;
import java.io.Console;
import java.io.IOException;


// Project files
import dev.mfrank.level.Level;
import dev.mfrank.level.Level0;
import dev.mfrank.level.Level1;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.context.ContextMenu;
import dev.mfrank.entity.Mage;
import dev.mfrank.utils.FileEngine;
import dev.mfrank.paladin.io.Io;


public class Main {

    // Global variables
    public static boolean gameRunning = false;
    public static Mage player;
    public static Paladin currentContext = new ContextMenu();
    public static boolean enableConsole = true;

    public static Level0 level0 = new Level0();
    public static Level1 level1 = new Level1();

    public static Level[] levelArray = new Level[] {
            level0,
            level1
    };

    public static void main(String[] args) throws IOException, InterruptedException {


        // Engine setup
        FileEngine.initConfig();
        FileEngine.readConfig();
        FileEngine.initSaves();


        // Console setup (if enabled)
        if (enableConsole) {
            Console console = System.console();
            if(console == null && !GraphicsEnvironment.isHeadless()){
                String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
                Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
            }
        }


       Io.printWelcome();                                            // !! Deprecated !!

       while(!gameRunning) {
           // todo: move functionality to Level0.java
           ContextMenu.interpret(Io.in());
       }

       // BEGIN GAME RUNTIME
       Debug.tell("Game start");

       // Run current level
       player.getCurrentLevel().run();
    }

    public static Mage getPlayer() {
        return player;
    }

    public static void setPlayer(Mage player) {
        Main.player = player;
    }

    public static Level getLevelById (int id) {
        for (Level level : levelArray) {
            if (level.getId() == id) {
                return level;
            }
        }
        return null;
    }
}