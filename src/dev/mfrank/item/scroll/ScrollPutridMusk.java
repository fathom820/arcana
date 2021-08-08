package dev.mfrank.item.scroll;

public class ScrollPutridMusk extends Scroll {

    public ScrollPutridMusk (int dropChance) {
        super();
        super.setId("scrollPutridMusk");
        super.setName("Putrid Musk Scroll");
        super.setDropChance(dropChance);
        super.setDescription("Unlocks the Putrid Musk spell.");
        super.setSpell("putrid musk");
    }
}
