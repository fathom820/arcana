package dev.mfrank.item.scroll;

import dev.mfrank.spell.Spell;

public class ScrollFireball extends Scroll {

    public ScrollFireball (int dropChance) {
        super();
        super.setName("Fireball Scroll");
        super.setDropChance(dropChance);
        super.setDescription("Unlocks the Fireball spell.");
        super.setSpell("Fireball");
    }
}
