package dev.mfrank.item.scroll;

import dev.mfrank.item.Item;

public class Scroll extends Item {

    private final String spell;

    public Scroll (String spell, int dropChance) {
        this.spell = spell;
        super.setDropChance(dropChance);
    }
}
