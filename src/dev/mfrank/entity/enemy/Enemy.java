package dev.mfrank.entity.enemy;

import dev.mfrank.Main;
import dev.mfrank.entity.Entity;
import dev.mfrank.entity.Mage;
import dev.mfrank.item.Item;
import dev.mfrank.item.scroll.Scroll;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

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

    public void attack (Mage m) {
        Attack atk = attacks[randInt(0, 2)];
        boolean hit = rand.nextInt(100) < atk.getPrecision();
        // TODO: pierce (use hit as blueprint)

        if (hit) {
            int dmg = super.randInt(atk.getDamageMin(), atk.getDamageMax());
            int netDmg = m.takeDamage((dmg));
            Io.tell(super.getName() + " attempted " + atk.getName() + " and hit for " + dmg + " damage.");

            if (m.getArmor() > 0) {
                Io.tell("Your armor absorbed half of the damage.");
                Io.tell("Net damage taken: " + netDmg);
            }

        } else {
            Io.tell(super.getName() + " attempted " + atk.getName() + " and missed.");

        }
    }

    public void die () {
        super.die();

        for (Item i : drops) {
            if (i.getType().equals("Scroll")) {
                Main.getPlayer().addSpell(i.getSpell());
                Debug.tell(Arrays.toString(Main.getPlayer().getSpells()));
            }
        }
    }

}
