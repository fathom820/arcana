package dev.mfrank.item;

public class Item {

    private int dropChance;
    private String description;

    public int getDropChance () {
        return dropChance;
    }

    public String getDescription() {
        return description;
    }

    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
