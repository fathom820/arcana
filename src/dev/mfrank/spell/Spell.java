package dev.mfrank.spell;

import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

public abstract class Spell {

    private String name;
    private int tier;
    private String description;
    private int damageMin;
    private int damageMax;
    private int piercing;
    private int precision;
    private int manaCost;


    public Spell () {

    }

    public void printInfo () {
        Io.setIndent(1);
        Io.tellRaw("-=| " + name.toUpperCase() + " [" + tierAsString() + "] |=-");
        Io.tellRaw(description);

        Io.printList(new String[] {
                "Minimum damage: " + damageMin,
                "Maximum damage: " + damageMax,
                "Piercing chance: " + piercing + "%",
                "Precision: " + precision+ "%",
                "Mana cost: " + manaCost
        });

        Io.setIndent(0);

    }

    private String tierAsString () {
        switch (tier) {
            case 1:
                return "I";

            case 2:
                return "II";

            case 3:
                return "III";
        }
        return "null";
    }
    // GETTERS

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public int getPiercing() {
        return piercing;
    }

    public int getPrecision() {
        return precision;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getTier() {
        return tier;
    }

    public String getTierAsString() {return tierAsString();};

    // SETTERS


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public void setPiercing(int piercing) {
        this.piercing = piercing;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
