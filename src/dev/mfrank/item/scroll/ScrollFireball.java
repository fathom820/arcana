package dev.mfrank.item.scroll;

public class ScrollFireball extends Scroll {

    public ScrollFireball (int dropChance) {
        super();
        super.setId("scrollFireball");
        super.setName("Fireball Scroll");
        super.setDropChance(dropChance);
        super.setDescription("Unlocks the Fireball spell.");
        super.setSpell("fireball");
    }

}
