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


public class Mage extends Entity {

    private int maxMana;
    private int mana;
    private Spell[] hotbar;
    private Spell[] spells;

    private Level currentLevel;
    private Random rand = new Random();

    public Mage() {
        super.setMaxHealth(100);
        super.setMaxArmor(50);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setName("NULL");
        this.maxMana = 100;
        this.mana = maxMana;
        this.hotbar = new Spell[3];
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
        this.hotbar = new Spell[3];
        this.spells = new Spell[] {};
        this.currentLevel = new Level0();

        Debug.tell(Arrays.toString(hotbar));
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

        out.append("\n# SCROLL\n");
        for (Spell s : hotbar) {
            if (s != null) {
                out.append("-scroll: ").append(s.getName());
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

    public boolean castSpell (Spell spell, Enemy enemy) {
        if (spell != null) {
            boolean hit = rand.nextInt(100) < spell.getPrecision();

            if (hit) {
                int dmg = randInt(spell.getDamageMin(), spell.getDamageMax());
                Io.tell(Main.getPlayer().getName() + " attempted " + spell.getName() + " and hit for " + enemy.takeDamage(dmg));
                return true;

            } else {
                Io.tell(Main.getPlayer().getName() + " attempted " + spell.getName() + " and missed.");
                return true;
            }
        } else {
            Io.tell("There is no spell assigned to this slot.");
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
        this.mana = mana;
    }

    public void setCurrentLevel (Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setHotbar(Spell[] hotbar) {
        this.hotbar = hotbar;
    }

    public void setSpells (Spell[] spells) {
        this.spells = spells;
    }

    public void setScrollSlot(int slot, Spell spell) {
        hotbar[slot] = spell;
    }

    // GETTERS
    public Level getCurrentLevel () {
        return currentLevel;
    }

    public Spell[] getHotbar() {
        return hotbar;
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