package dev.mfrank.entity.enemy;

import dev.mfrank.entity.Entity;
import dev.mfrank.entity.Mage;
import dev.mfrank.paladin.io.Io;

import java.util.Random;

public abstract class Enemy extends Entity {

    public Attack[] attacks = new Attack[3];
    Random rand = new Random();


    public void attack (Mage m) {
        Attack atk = attacks[randInt(0, 2)];
        boolean hit = rand.nextInt(100) < atk.getPrecision();

        if (hit) {
            int dmg = randInt(atk.getDamageMin(), atk.getDamageMax());
            Io.tellRaw(getName() + "attempted " + atk.getName() + "and hit for " + dmg);
            m.takeDamage(dmg);

        } else {
            Io.tellRaw(getName() + " attempted " + atk.getName() + "and missed.");

        }
    }

    private int randInt(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("randInt error: max must be greater than min");
        }

        return rand.nextInt((max - min) + 1) + min;
    }
}
