package dev.mfrank.item;

import dev.mfrank.spell.Spell;

public abstract class Item {

    private String name;
    private String type;
    private int dropChance;
    private String description;

    // getters //
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDropChance () {
        return dropChance;
    }

    public String getDescription() {
        return description;
    }

    // setters //
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Spell getSpell();
}
