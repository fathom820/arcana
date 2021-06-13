package dev.mfrank.spell;

public class SpellSpark extends Spell {

    public SpellSpark() {
        super.setName("Spark");
        super.setDescription("Creates a small but potent spark that inflicts minimal damage.");
        super.setDamageMin(5);
        super.setDamageMax(10);
        super.setPiercing(10);
        super.setPrecision(80);
    }

}
