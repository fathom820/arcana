package dev.mfrank;

// Java libraries
import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;


// Project files
import dev.mfrank.level.Level;
import dev.mfrank.level.Level0;
import dev.mfrank.level.Level1;
import dev.mfrank.paladin.Debug;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.context.Menu;
import dev.mfrank.entity.Mage;
import dev.mfrank.utils.FileEngine;
import dev.mfrank.utils.Text;


public class Main {

    // Global variables
    public static boolean gameRunning = false;
    public static Mage player;
    public static Paladin currentContext = new Menu();
    //public static Level currentLevel;
    public static boolean enableConsole = true;

    public static Level0 level0 = new Level0();
    public static Level1 level1 = new Level1();

    public static Level[] levelArray = new Level[] {
            level0,
            level1
    };

    public static void main(String[] args) throws IOException {


        // Engine setup
        FileEngine.initConfig();
        FileEngine.readConfig();
        FileEngine.initSaves();
        Scanner kb = new Scanner(System.in);


        new Menu();

        // Console setup (if enabled)
        if (enableConsole) {
            Console console = System.console();
            if(console == null && !GraphicsEnvironment.isHeadless()){
                String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
                Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
            }else{
                //Main.main(new String[0]);
                //System.out.println("Program has ended, please type 'exit' to close the console");
            }
        }


        Text.printWelcome();                                            // !! Deprecated !!

       while(!gameRunning) {
           // todo: move functionality to level0
           Menu.interpret(kb.nextLine());
       }

        // BEGIN GAME RUNTIME
        Debug.tell("Game start");
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