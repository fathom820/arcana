package dev.mfrank.entity;

import dev.mfrank.level.Level;
import dev.mfrank.level.Level0;
import dev.mfrank.spell.Spell;


public class Mage extends Entity {

    private int maxMana;
    private int mana;
    private Spell[] scroll;
    private Spell[] spells;

    private Level currentLevel;

    public Mage() {
        super.setMaxHealth(100);
        super.setMaxArmor(50);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setName("NULL");
        this.maxMana = 100;
        this.mana = maxMana;
        this.scroll = new Spell[] {};
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
        this.scroll = new Spell[] {};
        this.spells = new Spell[] {};
        this.currentLevel = new Level0();
    }

    /*
    --------------
    * DEPRECATED *
    --------------
    public String toString () {
        StringBuilder out =
            new StringBuilder(attributeToString("maxHealth", super.getMaxHealth()) +
                attributeToString("maxArmor", super.getMaxArmor()) +
                attributeToString("health", super.getHealth()) +
                attributeToString("armor", super.getArmor()) +
                attributeToString("name", super.getName()) +
                attributeToString("maxMana", maxMana) +
                attributeToString("mana", mana)
                + "Spells:\n");

        for (Spell spell : spells) {
            out.append("    ").append(spell.getName()).append("\n");
        }

        return out.toString();
    }
    */

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

        return out.toString();
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

    public void setScroll (Spell[] scroll) {
        this.scroll = scroll;
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