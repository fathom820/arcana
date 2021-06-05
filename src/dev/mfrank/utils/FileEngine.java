/*
This class contains several different functions pertaining to file data and parsing.
It allows the game to create new "saves" for different players, and
read the data from these "saves" to pick up where they left off.

Files are stored in the MAGE file format.
Any line starting with # will be ignored, and the rest will be read.
 */

package dev.mfrank.utils;

import dev.mfrank.Main;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.entity.Mage;
import dev.mfrank.paladin.io.Io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
// end import


public class FileEngine {

    private final static String winUser = System.getProperty("user.name");
    private final static String saveAddr = "C:\\Users\\" + winUser + "\\Documents\\My Games\\Arcana\\Mages";
    private final static String configAddr = "C:\\Users\\" + winUser + "\\Documents\\My Games\\Arcana\\Config";
    private final static File saveDir = new File(saveAddr);
    private final static File configDir = new File(configAddr);
    private final static File config = new File(configAddr + "\\" + "config.cfg");
    private static String currentFile;

    /*
    * CONSTRUCTOR *
    Attempt to locate save directory.
    If directory does not exist, create it.
     */
    public static void initSaves() throws IOException {

        if (!Files.exists(Paths.get(saveAddr))) {
            Io.tell(saveAddr + " not found. Creating...");
            if (saveDir.mkdirs()) {
                Io.tell(saveAddr + " successfully created.");
            } else {
                Io.tell("Unable to create " + saveAddr);
            }
        }
    }

    public static void initConfig() throws IOException {
        if (!Files.exists(Paths.get(configAddr))) {
            Io.tell(configAddr + " not found. Creating...");

            if (configDir.mkdirs()) {
                Io.tell(configAddr + " successfully created.");

                if (config.createNewFile()) {
                    Io.tell("Config file successfully created in " + configAddr);

                    // adds information section to config file. Will not regenerate if modified or removed.
                    FileWriter fileWriter = new FileWriter(config);
                    fileWriter.write(
                    "# DEVELOPER USE ONLY - DEVELOPER USE ONLY - DEVELOPER USE ONLY\n" +
                        "# Config file for Arcana. Depending what you write in this file, different elements of the program\n" +
                        "# can be enabled or disabled. Arguments must be separated by lines.\n" +
                        "# ARGUMENTS:\n" +
                        "# noconsole: disables the console that the game runs through. Intended for testing purposes.\n" +
                        "# debug: enables debug messages by default.\n" +
                        "# ------------------------------------------------------------------------------------------------\n"
                    );
                    fileWriter.close();
                }
            } else {
                Io.tell("Unable to create " + configAddr);
            }
        }
    }

    // General functions

    /*
    Sets current file to whatever parameter is passed.
     */
    public static void setCurrentMage(String fname) {
        currentFile = saveAddr + "\\" + fname + ".mage";
    }

    /*
    Creates a new mage with default attributes. Name is set to whatever parameter is passed.
     */
    public static boolean newMage (String fname) throws IOException {
        File newSaveFile = new File(saveDir + "\\" + fname + ".mage");
        if (newSaveFile != null) {
            boolean saveSuccess = newSaveFile.createNewFile();
            if (saveSuccess) {
                Debug.tell("File " + fname + ".mage created.");
                Main.gameRunning = true;

            } else {
                Io.tell("A mage with this name already exists.\n" +
                        "Enter \"new\" again if you'd like to try a different name.");
            }
            return saveSuccess;
        } else {
            Io.tell("FileEngine not configured. Aborting newFile()");
            return false;
        }
    }

    /*
    This function creates a new player using values from the current configured file
    in the file engine. It then returns that player.
     */
    public static Mage loadMage() throws FileNotFoundException {
        Io.tell("Which file would you like to load?");
        listMages();
        File loadedMage = new File(saveAddr + "\\" + Io.in() + ".mage");
        Mage player = new Mage();

        if (loadedMage.isFile()) {
            Scanner fileReader = new Scanner(loadedMage);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                if (line.charAt(0) != '#') {
                    String[] splitLine = line.split("=");
                    String val = splitLine[1];

                    switch (splitLine[0]) {
                        case "maxHealth":
                            player.setMaxHealth(Integer.parseInt(val));
                        break;

                        case "maxArmor":
                            player.setMaxArmor(Integer.parseInt(val));
                        break;

                        case "health":
                            player.setHealth(Integer.parseInt(val));
                        break;

                        case "armor":
                            player.setArmor(Integer.parseInt(val));
                        break;

                        case "name":
                            player.setName(val);
                        break;

                        case "maxMana":
                            player.setMaxMana(Integer.parseInt(val));
                        break;

                        case "mana":
                            player.setMana(Integer.parseInt(val));
                        break;

                        case "currentLevel":
                            player.setCurrentLevel(Main.getLevelById(Integer.parseInt(val)));
                    }
                }
            }

            Debug.tell("Successfully loaded mage attributes.");
            Debug.tell(player.toSaveFormat());
            Main.gameRunning = true;
        }

        else {
            Io.tell("This mage does not exist. Make sure you typed in the name correctly.");
        }

        return player;
    }

    public static void saveMage(Mage player) throws IOException {
        FileWriter fileWriter = new FileWriter(currentFile);
        fileWriter.write("# Player save file for Arcana.\n# **DO NOT MODIFY!**\n" + player.toSaveFormat());
        fileWriter.close();
    }

    public static void listMages() {
        Io.printDivider(25);
        Io.tellRaw("List of all saved mages:");
        Io.setIndent(1);
        String[] files = saveDir.list();
        assert files != null;

        for (String f : files) {
            Io.tellRaw(f);
        }
        Io.setIndent(0);
        Io.printDivider(25);
    }

    public static void deleteMage () {
        setCurrentMage(Io.in());
        File mage = new File(currentFile);

        if (mage.delete()) {
            Io.tell("File deleted.");
        } else {
            Io.tell("File could not be deleted.");
        }
    }

    public static void readConfig() throws FileNotFoundException {

        Scanner fileReader = new Scanner(config);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();

            if (line.charAt(0) != '#') { // # precedes a comment, signalling the line to be ignored

                switch(line) {
                    case "debug":
                        Debug.setState(true);
                        Io.tell("Config: debug enabled");
                        break;

                    case "noconsole":
                        Main.enableConsole = false;
                        Io.tell("Config: console disabled");
                        break;
                }
            }
        }
    }
}