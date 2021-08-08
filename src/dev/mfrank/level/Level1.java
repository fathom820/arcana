package dev.mfrank.level;

import dev.mfrank.entity.enemy.lvl1.EnemySkeleton;
import dev.mfrank.entity.enemy.lvl1.EnemyZombie;
import dev.mfrank.paladin.context.ContextBattle;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

import java.io.IOException;

public class Level1 extends Level {

    private ContextBattle stage1, stage2;

    public Level1 () {
        super.setId(1);
        super.setName("LEVEL 1");
        super.setInfo("Welcome to Level 1! As you descend into the dungeon, the smell of\n" +
            "dust and bones permeates the air. Out of the darkness, you hear creaking...");
        Io.setIndent(0);
        super.setComplete(false);

        stage1 = new ContextBattle(new EnemySkeleton());
        stage2 = new ContextBattle(new EnemySkeleton());
    }

    public void run () throws IOException, InterruptedException {
        Debug.tell("Begin level 1");
        Io.printDivider();
        Io.tellRaw(getName());
        Io.setIndent(1);
        Io.tellRaw(getInfo());
        Io.longPause();


        Debug.tell("stage1");
        stage1.run();

        Debug.tell("stage2");
        stage2.run();

        Debug.tell("stage3");
        new ContextBattle(new EnemyZombie()).run();

        Debug.tell("stage4");
        new ContextBattle(new EnemyZombie()).run();
    }
}