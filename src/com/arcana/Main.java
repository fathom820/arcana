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

    public static void main(String[] args) throws IOException {

        // Startup sequence and loading
        Debug.toggle();
        Input.init();
        Scanner kb = new Scanner(System.in);
        FileEngine fileEng = new FileEngine();
        Player player = null;


        Text.printWelcome();

        while (!gameRunning) {
            System.out.print("Enter the name of your Mage: ");
            player = new Player(kb.nextLine());
            gameRunning = fileEng.newFile(player.getName());
        }


        // BEGIN GAME RUNTIME //
        Debug.tell("Game start");
        while (gameRunning) {
            String input = kb.nextLine();
            Input.interpret(input);
        }


    }
}
