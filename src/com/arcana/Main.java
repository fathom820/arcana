package com.arcana;

// Java libraries //
import java.io.IOException;
import java.util.Scanner;


// Project files //
import com.arcana.config.Debug;
import com.arcana.entity.*;
import com.arcana.input.Input;
import com.arcana.utils.*;


public class Main {

    public static boolean gameRunning = false;
    public static Player player;

    public static void main(String[] args) throws IOException {

        // Startup sequence and loading
        Debug.toggle();
        Input.init();
        Scanner kb = new Scanner(System.in);
        FileEngine fileEng = new FileEngine();

        Text.printWelcome();

        while (!gameRunning) {
            System.out.print("Enter the name of your Mage: ");
            setPlayer(new Player(kb.nextLine()));
            FileEngine.configure(getPlayer().getName());
            gameRunning = FileEngine.newFile(getPlayer().getName());
            FileEngine.saveFile(player);
            FileEngine.loadFile();
        }



        // BEGIN GAME RUNTIME //
        Debug.tell("Game start");
        while (gameRunning) {
            String input = kb.nextLine();
            Input.interpret(input);
        }


    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Main.player = player;
    }
}
