package dev.mfrank.spell;

public class Spell {

    private String name;
    private String damageMin;
    private String damageMax;
    private String piercing;
    private String precision;

    private Spell[] spells;

    public Spell getById (String id) {
        for (Spell s : spells) {
            if (s.getName().equals(id)) {
                return s;
            }
        }

        return null;
    }


    // GETTERS

    public String getName() {
        return name;
    }

    public String getDamageMin() {
        return damageMin;
    }

    public String getDamageMax() {
        return damageMax;
    }

    public String getPiercing() {
        return piercing;
    }

    public String getPrecision() {
        return precision;
    }
}
