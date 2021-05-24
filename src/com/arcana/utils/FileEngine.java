/*
This class contains several different functions pertaining to file data and parsing.
It allows the game to create new "saves" for different players, and
read the data from these "saves" to pick up where they left off.
 */

package com.arcana.utils;

import com.arcana.config.Debug;
import com.arcana.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
// end import


public class FileEngine {

    private final static String winUser = System.getProperty("user.name");
    private final static String saveDir = "C:\\Users\\" + winUser + "\\Documents\\My Games\\Arcana";
    private static String currentFile;

    /*
    * CONSTRUCTOR *
    Attempt to locate save directory.
    If directory does not exist, create it.
     */
    public FileEngine () {

        if (!Files.exists(Paths.get(saveDir))) {

            Debug.warn(saveDir + " not found. Creating...");

            try {
                Files.createDirectory(Paths.get(saveDir));
                Debug.warn(saveDir + " successfully created.");
            } catch (IOException io) {
                Debug.warn("Unable to create " + saveDir);
            }

        }
    }

    public static void configure(String fname) {
        currentFile = saveDir + "\\" + fname + ".mage";
    }

    public static boolean newFile(String fname) throws IOException {
        File save = new File (currentFile);
        if (save != null) {
            boolean saveSuccess = save.createNewFile();
            if (saveSuccess) {
                Debug.tell("File " + fname + ".mage created.");
            } else {
                Debug.warn("A mage with this name already exists. Please try another name.");
            }
            return saveSuccess;
        } else {
            Debug.warn("FileEngine not configured. Aborting newFile()");
            return false;
        }


    }

    /*
    This function creates a new player using values from the current configured file
    in the file engine. It then returns that player.
     */
    public static Player loadFile () {
        Player player = new Player();

        try {
            Debug.tell("Loading " + currentFile);
            File save = new File(currentFile);
            Scanner fileReader = new Scanner(save);
            while(fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (data.charAt(0) != '#') {
                    Debug.tell(data);
                }

            }

        } catch (FileNotFoundException e) {
            Debug.warn("Error in FileEngine.loadFile(): Unable to load file " + currentFile);
            e.printStackTrace();
        }

        return player;
    }

    public static void saveFile (Player player) throws IOException {
        FileWriter fileWriter = new FileWriter(currentFile);
        fileWriter.write("# Player save file for Arcana.\n# **DO NOT MODIFY!**\n");
        fileWriter.write(player.toSaveFormat());
        fileWriter.close();
    }
}
