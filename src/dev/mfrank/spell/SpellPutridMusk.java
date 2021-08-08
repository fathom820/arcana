package dev.mfrank.spell;

public class SpellPutridMusk extends Spell {
    public SpellPutridMusk () {
        super.setName("putrid musk");
        super.setDescription("Emits a foul-smelling odor that covers a wide area.");
        super.setDamageMin(5);
        super.setDamageMax(18);
        super.setPiercing(0);
        super.setPrecision(100);
        super.setManaCost(5);
        super.setTier(1);
    }

}
