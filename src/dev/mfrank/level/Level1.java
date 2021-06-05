package dev.mfrank.level;

import dev.mfrank.Main;

import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

public class Level1 extends Level {

    public Level1 () {

    }

    public void run () {
        Debug.tell("Begin level 1");
        Io.tellRaw("Welcome to Arcana, " + Main.player.getName());

    }
}
