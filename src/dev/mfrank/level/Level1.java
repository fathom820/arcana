package dev.mfrank.level;

import dev.mfrank.Main;

import dev.mfrank.paladin.Debug;
import dev.mfrank.paladin.Io;

public class Level1 extends Level {

    public Level1 () {

    }

    public void run () {
        Debug.tell("Begin level 1");
        Io.tellRaw("Welcome to Arcana, " + Main.player.getName());

    }
}
