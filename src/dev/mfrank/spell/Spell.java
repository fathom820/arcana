package dev.mfrank.spell;

import dev.mfrank.paladin.io.Debug;

public abstract class Spell {

    private String name;
    private String description;
    private int damageMin;
    private int damageMax;
    private int piercing;
    private int precision;


    public Spell () {

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
}
