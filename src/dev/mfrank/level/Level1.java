package dev.mfrank.level;

import dev.mfrank.Main;

import dev.mfrank.paladin.Debug;

public class Level1 extends Level {

    public Level1 () {

    }

    public void run () {
        Debug.tell("Begin level 1");
        System.out.printf("Welcome to Arcana, %s!\n", Main.player.getName());
    }
}
