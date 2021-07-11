package dev.mfrank.entity;

import dev.mfrank.Main;
import dev.mfrank.entity.enemy.Enemy;
import dev.mfrank.level.Level;
import dev.mfrank.level.Level0;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.Spell;

import java.util.Arrays;
import java.util.Random;

/*
    Class representing the mage (player)
    Only one instance of this class exists (Main.player)
 */
public class Mage extends Entity {

    private int maxMana;
    private int mana;
    private Spell[] spells;

    private Level currentLevel;
    private final Random rand = new Random();

    public Mage() {
        super.setMaxHealth(100);
        super.setMaxArmor(50);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setName("NULL");
        this.maxMana = 100;
        this.mana = maxMana;
        this.spells = new Spell[] {};
        this.currentLevel = new Level0();
    }

    public Mage(String name) {
        super.setMaxHealth(100);
        super.setMaxArmor(50);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setName(name);
        this.maxMana = 100;
        this.mana = maxMana;
        this.spells = new Spell[] {};
        this.currentLevel = new Level0();
    }


    public String toSaveFormat () {
        StringBuilder out = new StringBuilder();
        String[] attrArray = new String[] {
            attributeToString("maxHealth", super.getMaxHealth()),
            attributeToString("maxArmor", super.getMaxArmor()),
            attributeToString("health", super.getHealth()),
            attributeToString("armor", super.getArmor()),
            attributeToString("name", super.getName()),
            attributeToString("maxMana", maxMana),
            attributeToString("mana", mana),
            attributeToString("currentLevel", currentLevel.getId())
        };
        for (String attr : attrArray) {
            out.append(attr).append("\n");
        }

        out.append("# SPELLS\n");
        for (Spell s : spells) {
            if (s != null) {
                out.append("-spell: ").append(s.getName());
            }
        }
        return out.toString();
    }

    public void addSpell(Spell spell) {
        Spell[] temp = new Spell[spells.length + 1];

        for (int i = 0; i < spells.length; i++) {
            temp[i] = spells[i];
        }

        temp[temp.length - 1] = spell;

        spells = temp;
    }

    public boolean castSpell (Spell spell, Enemy enemy) throws InterruptedException {

        if (spell != null && hasSpell(spell) && mana >= spell.getManaCost()) {
            boolean hit = rand.nextInt(100) < spell.getPrecision();
            mana -= spell.getManaCost();
            Io.tell(spell.getManaCost() + " mana was used.");

            if (hit) {
                int dmg = randInt(spell.getDamageMin(), spell.getDamageMax());
                Io.tell(Main.getPlayer().getName() + " attempted " + spell.getName() + " and hit for " + enemy.takeDamage(dmg));
                Io.pause();
                setMana(getMana() + (spell.getManaCost() / 2));
                Io.tell("Since you hit your target, some mana has been returned.");

            } else {
                Io.tell(Main.getPlayer().getName() + " attempted " + spell.getName() + " and missed.");

            }
            return true;

        } else {
            if (spell == null) {
                Io.tell("Error in Mage.castSpell(): spell == null");
            } else if (!hasSpell(spell)) {
                Io.tell("You do not possess this spell. Make sure you've typed the name correctly.");
            } else if (mana <= 0) {
                mana = 0;
                Io.tell("It seems you're out of mana. You should skip your turn to regenerate.");
            } else { // not enough mana
                Io.tell("You don't have enough mana to cast " + spell.getName());
                Io.setIndent(1);
                Io.tellRaw("Required mana: " + spell.getManaCost());
                Io.tellRaw("Your mana: " + mana + "/" + getMaxMana());
                Io.setIndent(0);
            }

        }
        return false;
    }

    public boolean hasSpell (Spell spell) {
        for (Spell s : spells) {
            if (s == spell) {
                return true;
            }
        }
        return false;
    }

    // AS INT
    private String attributeToString (String attrName, int attrVal) {
        return attrName + "=" + attrVal;
    }

    // AS STRING
    private String attributeToString (String attrName, String attrVal) {
        return attrName + "=" + attrVal;
    }


    // SETTERS

    public void setMaxMana (int maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana (int mana) {
        if (mana > maxMana) {
            mana = maxMana;
        }
        this.mana = mana;

    }

    public void setCurrentLevel (Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setSpells (Spell[] spells) {
        this.spells = spells;
    }


    // GETTERS
    public Level getCurrentLevel () {
        return currentLevel;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }
}