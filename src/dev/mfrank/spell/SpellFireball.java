package dev.mfrank.spell;

public class SpellFireball extends Spell{

    public SpellFireball () {
        super.setName("Fireball");
        super.setDescription("Conjures a high-temperature fireball that inflicts low damage.");
        super.setDamageMin(10);
        super.setDamageMax(15);
        super.setPiercing(25);
        super.setPrecision(75);
        super.setManaCost(25);
    }

}
