package dev.mfrank;

// Java libraries
import java.io.IOException;
import java.util.Scanner;


// Project files
import dev.mfrank.paladin.Debug;
import dev.mfrank.paladin.Stage;
import dev.mfrank.paladin.stage.Menu;
import dev.mfrank.entity.Player;
import dev.mfrank.utils.Text;


public class Main {

    // Global variables
    public static boolean gameRunning = false;
    public static Player player;
    public static Stage currentLevel = new Menu();


    public static void main(String[] args) throws IOException {

        // Startup sequence and loading
        Debug.toggle();                                                 // show debug messages
        Scanner kb = new Scanner(System.in);                            // I/O scanner


        Text.printWelcome();                                            // !! Deprecated !!

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