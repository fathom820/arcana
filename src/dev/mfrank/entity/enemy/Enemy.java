package dev.mfrank.entity.enemy;

import dev.mfrank.entity.Entity;
import dev.mfrank.entity.Mage;
import dev.mfrank.paladin.io.Io;

import java.util.Random;

public abstract class Enemy extends Entity {

    public Attack[] attacks = new Attack[3];
    Random rand = new Random();

    public Enemy() {
        super();
    }


    public void attack (Mage m) {
        Attack atk = attacks[randInt(0, 2)];
        boolean hit = rand.nextInt(100) < atk.getPrecision();

        if (hit) {
            int dmg = super.randInt(atk.getDamageMin(), atk.getDamageMax());
            int netDmg = m.takeDamage((dmg));
            Io.tell(super.getName() + " attempted " + atk.getName() + " and hit for " + dmg + "damage.");

            if (m.getArmor() > 0) {
                Io.tell("Your armor absorbed half of the damage.");
                Io.tell("Net damage taken: " + netDmg);
            }

        } else {
            Io.tell(super.getName() + " attempted " + atk.getName() + " and missed.");

        }
    }

}
