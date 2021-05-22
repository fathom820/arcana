/*
This class contains several different functions pertaining to file data and parsing.
It allows the game to create new "saves" for different players, and
read the data from these "saves" to pick up where they left off.
 */

package com.arcana.utils;

import com.arcana.config.Debug;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
// end import


public class FileEngine {

    private final String winUser = System.getProperty("user.name");
    private final String saveDir = "C:\\Users\\" + winUser + "\\Documents\\My Games\\Arcana";


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

    public boolean newFile(String fname) throws IOException {
        File save = new File (saveDir + "\\" + fname + ".dat");
        boolean saveSuccess = save.createNewFile();
        if (saveSuccess) {
            Debug.tell("File " + fname + ".dat created.");
        } else {
            Debug.warn("A mage with this name already exists. Please try another name.");
        }
        return saveSuccess;

    }


    public void loadFile (String fname) {
        // todo
    }

    public void saveFile (String fname) {

    }
}
