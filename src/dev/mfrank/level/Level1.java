package dev.mfrank.level;

import dev.mfrank.Main;

import dev.mfrank.entity.enemy.EnemySkeleton;
import dev.mfrank.paladin.context.ContextBattle;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

import java.io.IOException;

public class Level1 extends Level {

    private EnemySkeleton testSkele;
    private ContextBattle stage1;

    public Level1 () {
        super.setId(1);
        super.setName("LEVEL 1");
        super.setInfo(Io.wrap("Welcome to Level 1! This is the first level of the Dungeon.", 1));
        Io.setIndent(0);
        super.setComplete(false);
        testSkele = new EnemySkeleton();
        stage1 = new ContextBattle(testSkele);
    }

    public void run () throws IOException, InterruptedException {
        Debug.tell("Begin level 1");
        Io.printDivider();
        Io.tellRaw(getName());
        Io.setIndent(1);
        Io.tellRaw(getInfo());


        // TODO: implement battle context and test out new enemy functionality

        stage1.run();
    }
}