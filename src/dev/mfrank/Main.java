package dev.mfrank;

// Java libraries
import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;


// Project files
import dev.mfrank.level.Level;
import dev.mfrank.paladin.Debug;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.stage.Menu;
import dev.mfrank.entity.Player;
import dev.mfrank.utils.FileEngine;
import dev.mfrank.utils.Text;


public class Main {

    // Global variables
    public static boolean gameRunning = false;
    public static Player player;
    public static Paladin currentContext = new Menu();
    public static Level currentLevel;
    public static boolean enableConsole = true;


    public static void main(String[] args) throws IOException {



        // Engine setup
        FileEngine.initConfig();
        FileEngine.readConfig();
        FileEngine.initSaves();
        Scanner kb = new Scanner(System.in);

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
           Menu.interpret(kb.nextLine());
       }

        // BEGIN GAME RUNTIME
        Debug.tell("Game start");
        currentLevel.run();
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Main.player = player;
    }
}