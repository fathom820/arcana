package dev.mfrank.engine;

import dev.mfrank.spell.Spell;
import dev.mfrank.spell.SpellEmpty;
import dev.mfrank.spell.SpellSpark;

public class SpellEngine {

    private static Spell[] spells;

    public static void init() {
        spells = new Spell[] {
            new SpellSpark(),
            new SpellEmpty()
        };
    }

    public static Spell getById(String id) {
        for (Spell s : spells) {
            if (s.getName().equals(id)) {
                return s;
            }
        }
        //Debug.tell("Spell.getById(): spell not found.");
        return null;
    }
}
