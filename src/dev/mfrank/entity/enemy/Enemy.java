package dev.mfrank.entity.enemy;

import dev.mfrank.Main;
import dev.mfrank.entity.Entity;
import dev.mfrank.entity.Mage;
import dev.mfrank.item.Item;
import dev.mfrank.item.scroll.Scroll;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.Spell;

import java.util.Arrays;
import java.util.Random;

public abstract class Enemy extends Entity {

    public Attack[] attacks = new Attack[3];
    private Item[] drops;
    private Random rand = new Random();

    public Enemy() {
        super();
        drops = new Item[]{};
    }

    public void addItemDrop(Item item) {
        Item[] temp = new Item[drops.length + 1];

        for (int i = 0; i < drops.length; i ++) {
            temp[i] = drops[i];
        }

        temp[temp.length - 1] = item;
        drops = temp;
    }

    public void attack (Mage m) throws InterruptedException {
        Attack atk = attacks[randInt(0, 2)];
        boolean hit = rand.nextInt(100) < atk.getPrecision();
        // TODO: pierce (use hit as blueprint)

        if (hit) {
            int dmg = super.randInt(atk.getDamageMin(), atk.getDamageMax());
            int netDmg = m.takeDamage((dmg));
            Io.pause();
            Io.tell(super.getName() + " attempted " + atk.getName() + " and hit for " + dmg + " damage.");

            if (m.getArmor() > 0) {
                Io.pause();
                Io.tell("Your armor absorbed half of the damage.");
                Io.pause();
                Io.tell("Net damage taken: " + netDmg);
            }

        } else {
            Io.pause();
            Io.tell(super.getName() + " attempted " + atk.getName() + " and missed.");

        }
    }

    public void die () throws InterruptedException {
        super.die(); // sets "alive" to false

        for (Item i : drops) {
            boolean dropped = rand.nextInt(100) < i.getDropChance();
            boolean scrollIncluded = false;

            if (dropped) {
                Io.tell(getName() + " dropped " + i.getName() + " (" + i.getDropChance() + "% chance)");

                if (i.getType().equals("Scroll")) {
                    Spell spell = i.getSpell();

                    if (!Main.getPlayer().hasSpell(spell)) {
                        scrollIncluded = true;
                        Main.getPlayer().addSpell(spell);
                        Io.pause();
                        Io.tell(Main.getPlayer().getName() + " has unlocked the spell " + spell.getName().toUpperCase() + " [" + spell.getTierAsString() + "]");
                    } else {
                        Io.pause();
                        Io.tell("You already possess the spell that this scroll unlocks.");
                    }

                }
            }

            if (scrollIncluded) {
                Io.tell("For more information on new spells, use the \"spells\" command.");
            }

        }
    }

}
