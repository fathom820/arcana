package com.arcana.entity;

import com.arcana.spell.Spell;


public class Player extends Entity {

    private int maxMana;
    private int mana;
    private Spell[] spells;
    private int currentLevelID;

    public Player() {
        super.setMaxHealth(100);
        super.setMaxArmor(50);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setName("NULL");
        this.maxMana = 100;
        this.mana = maxMana;
        this.spells = new Spell[] {};
        this.currentLevelID = 0;
    }

    public Player(String name) {
        super.setMaxHealth(100);
        super.setMaxArmor(50);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setName(name);
        this.maxMana = 100;
        this.mana = maxMana;
        this.spells = new Spell[] {};
        this.currentLevelID = 0;
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
            attributeToString("storyLevel", currentLevelID)
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

}
