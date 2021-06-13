package dev.mfrank;

// Java libraries
import java.awt.*;
import java.io.Console;
import java.io.IOException;


// Project files
import dev.mfrank.engine.SpellEngine;
import dev.mfrank.level.Level;
import dev.mfrank.level.Level0;
import dev.mfrank.level.Level1;
import dev.mfrank.entity.Mage;
import dev.mfrank.engine.FileEngine;


public class Main {

    // Global variables
    public static boolean gameRunning = false;
    private static Mage player;
    public static boolean enableConsole = true;


    public static Level[] levelArray = new Level[]{
        new Level0(),
        new Level1()
    };

    public static void main(String[] args) throws IOException, InterruptedException {

        // Engine setup
        SpellEngine.init();
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


       levelArray[0].run(); // run level 0 (main menu), from here the next level will be determined

    }

    // GETTERS

    public static Mage getPlayer() {
        return player;
    }

    public static boolean getGameRunning() {return gameRunning;}

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