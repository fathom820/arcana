package com.arcana;

// Java libraries
import java.io.IOException;
import java.util.Scanner;


// Project files
import com.arcana.paladin.Debug;
import com.arcana.entity.*;
import com.arcana.paladin.Stage;
import com.arcana.paladin.stage.Menu;
import com.arcana.utils.*;


public class Main {

    // Global variables
    public static boolean gameRunning = false;
    public static Player player;
    public static Stage currentLevel = new Menu();


    public static void main(String[] args) throws IOException {

        // Startup sequence and loading
        Debug.toggle();                                                 // show debug messages
        Scanner kb = new Scanner(System.in);                            // I/O scanner


        Text.printWelcome();                                            // !! DEPRECATED !!

        while (currentLevel.getLevelID() == 0) {
            currentLevel.interpret(kb.nextLine());
        }


        // BEGIN GAME RUNTIME
        Debug.tell("Game start");
        while (gameRunning) {
            String input = kb.nextLine();
            currentLevel.interpret(input);
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Main.player = player;
    }
}